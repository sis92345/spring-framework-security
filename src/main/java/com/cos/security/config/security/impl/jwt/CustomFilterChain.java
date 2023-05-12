package com.cos.security.config.security.impl.jwt;

import com.cos.security.base.annotation.SecurityFilterChain;
import com.cos.security.base.constant.SecurityType;
import com.cos.security.base.security.filter.JWTAuthenticationFilter;
import com.cos.security.config.security.SecurityFilterChainConfigurer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@SecurityFilterChain
@RequiredArgsConstructor
@Slf4j
public class CustomFilterChain implements SecurityFilterChainConfigurer {
		
		private final AuthenticationConfiguration authenticationConfiguration;
		
		@Override
		public HttpSecurity configure( HttpSecurity sec ) throws Exception {
				
				log.info( "Security Custom Filter Chain 등록중" );
				
				//sec.addFilterAfter( new AuthFilter(), SecurityContextHolderFilter.class );
				
				// UsernamePasswordAuthenticationFilter 추가 -> /login으로 endpoint 생성
				sec.addFilter( new JWTAuthenticationFilter( authenticationManager() ) );
				return sec;
		}
		
		@Override
		public SecurityType getSecurityType() {
				return SecurityType.JWT;
		}
		
		private AuthenticationManager authenticationManager () throws Exception {
				return authenticationConfiguration.getAuthenticationManager();
		}
}
