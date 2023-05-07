package com.cos.security.base.security.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * 권한이나 인증이 필요할 때 이 필터가 작동합니다.
 * */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
		
		public JWTAuthorizationFilter( AuthenticationManager authenticationManager ) {
				super( authenticationManager );
		}
		
		public JWTAuthorizationFilter( AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint ) {
				super( authenticationManager, authenticationEntryPoint );
		}
}
