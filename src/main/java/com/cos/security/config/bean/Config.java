package com.cos.security.config.bean;

import com.cos.security.base.constant.SecurityType;
import com.cos.security.base.security.filterChain.SecurityFilterChainConfigurerFactory;
import com.cos.security.base.security.filterChain.factory.FormSecurityFilterChainConfigurerFactory;
import com.cos.security.base.security.filterChain.factory.JWTSecurityFilterChainConfigurerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class Config {
		
		@Bean
		public SecurityFilterChainConfigurerFactory securityFilterChainConfigurerFactory ( @Value( "${spring-sec.filter.chain}" ) String filterType,
						@Autowired FormSecurityFilterChainConfigurerFactory formSecurityFilterChainConfigurerFactory,
						@Autowired JWTSecurityFilterChainConfigurerFactory jwtSecurityFilterChainConfigurerFactory
					) {
				
				if ( SecurityType.FORM.getKey().equals( filterType ) ) {
						return formSecurityFilterChainConfigurerFactory;
				}
				else if ( SecurityType.JWT.getKey().equals( filterType )  ) {
						return jwtSecurityFilterChainConfigurerFactory;
				}
				
				throw new RuntimeException( "시큐리티 설정 필수" );
		}
		
		/**
		 * Spring Security 인증과 함께 사용하기 위해서 @CrossOrigin이 아닌 필터 걸어야 함
		 * */
		@Bean
		public CorsFilter corsFilter () {
				
				UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
				
				CorsConfiguration cors = new CorsConfiguration();
				cors.setAllowCredentials( true );
				cors.addAllowedHeader( "*" );
				cors.addAllowedMethod( "*" );
				cors.addAllowedOrigin( "*" );
				source.registerCorsConfiguration( "/api/**" , cors );
				
				return new CorsFilter( source );
		}
		
}
