package com.cos.security.base.security.filterChain.impl.jwt;

import com.cos.security.base.annotation.SecurityFilterChain;
import com.cos.security.base.constant.SecurityType;
import com.cos.security.base.security.filterChain.SecurityFilterChainConfigurer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

@SecurityFilterChain
@Slf4j
public class SessionFilterChain implements SecurityFilterChainConfigurer {
		
		@Override
		public HttpSecurity configure( HttpSecurity sec ) throws Exception {
				
				log.info( "Session 정책 처리중" );
				
				sec.sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS );
				
				return sec;
		}
		
		@Override
		public SecurityType getSecurityType() {
				return SecurityType.JWT;
		}
}
