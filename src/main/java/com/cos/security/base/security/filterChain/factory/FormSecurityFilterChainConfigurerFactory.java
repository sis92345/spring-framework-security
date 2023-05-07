package com.cos.security.base.security.filterChain.factory;

import com.cos.security.base.constant.SecurityType;
import com.cos.security.base.security.filterChain.SecurityFilterChainConfigurer;
import com.cos.security.base.security.filterChain.SecurityFilterChainConfigurerFactory;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Form Security Filter Chain
 * */
@Component
@RequiredArgsConstructor
@Slf4j
public class FormSecurityFilterChainConfigurerFactory implements SecurityFilterChainConfigurerFactory {

		Map<String, SecurityFilterChainConfigurer> configurerMap = new HashMap<>();
		
		private final ApplicationContext applicationContext;
		
		
		@PostConstruct
		void init () {
				
				Map<String,SecurityFilterChainConfigurer> map = applicationContext.getBeansOfType( SecurityFilterChainConfigurer.class );
				
				map = map.entrySet().stream().filter( entry ->  SecurityType.FORM.checkType().apply( entry ) )
								  			 .collect( Collectors.toMap( Map.Entry::getKey , Map.Entry::getValue ) );
				
				configurerMap.putAll( map );
		}
		
		/**
		 * SecurityFilterChainConfigurer를 모두 실행해서 FilterChain을 생성합니다.
		 * */
		public void setSecurityConfig ( HttpSecurity security ) {
				
				log.info( "Form Security 설정 사용" );
				
				if ( configurerMap == null ) {
						return;
				}
				
				configurerMap.forEach( ( key , item ) -> {
						
						try {
								item.configure( security );
						}
						catch ( Exception e ) {
								throw new RuntimeException( "Security Filter Chain 설정 중 문제 발생" );
						}
						
				});
				
		}
		
		/**
		 * SecurityFilterChainConfigurer를 반환시킵니다
		 *
		 * @param beanName
		 * @return SecurityFilterChainConfigurer
		 * */
		protected SecurityFilterChainConfigurer getConfig ( String beanName ) {
				return configurerMap.get( beanName );
		}
}
