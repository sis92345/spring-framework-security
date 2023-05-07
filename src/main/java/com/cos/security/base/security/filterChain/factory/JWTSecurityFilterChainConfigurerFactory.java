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
 * JWT에서 사용하는 Filter Chain
 * */
@Component
@RequiredArgsConstructor
@Slf4j
public class JWTSecurityFilterChainConfigurerFactory implements SecurityFilterChainConfigurerFactory {
		
		Map<String, SecurityFilterChainConfigurer> configurerMap = new HashMap<>();
		
		private final ApplicationContext applicationContext;
		
		@PostConstruct
		void init () {
				
				Map<String,SecurityFilterChainConfigurer> map = applicationContext.getBeansOfType( SecurityFilterChainConfigurer.class );
				
				map = map.entrySet().stream().filter( entry -> SecurityType.JWT.checkType().apply( entry ) )
								  			 .collect( Collectors.toMap( Map.Entry::getKey , Map.Entry::getValue ) );
				
				configurerMap.putAll( map );
		}
		
		@Override
		public void setSecurityConfig( HttpSecurity security ) {
				
				log.info( "JWT Security 설정 사용" );
				
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
		
}
