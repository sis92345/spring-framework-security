package com.cos.security.base.security.filterChain;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 설정된 SecurityFilterChain을 설정을 모아서 반환시킵니다.
 * */
@Component
@RequiredArgsConstructor
public class SecurityFilterChainConfigurerFactory {

		Map<String,SecurityFilterChainConfigurer> configurerMap = new HashMap<>();
		
		private final ApplicationContext applicationContext;
		
		@PostConstruct
		void init () {
				
				Map<String,SecurityFilterChainConfigurer> map = applicationContext.getBeansOfType( SecurityFilterChainConfigurer.class );
				configurerMap.putAll( map );
		}
		
		/**
		 * SecurityFilterChainConfigurer를 모두 실행해서 FilterChain을 생성합니다.
		 *
		 * */
		public void setSecurityConfig ( HttpSecurity security ) {
				
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
		public SecurityFilterChainConfigurer getConfig ( String beanName ) {
				return configurerMap.get( beanName );
		}
}
