package com.cos.security.config.security.impl.jwt;

import com.cos.security.base.constant.SecurityType;
import com.cos.security.config.security.SecurityFilterChainConfigurer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JWTHttpFilterChain implements SecurityFilterChainConfigurer {
		
		@Override
		public HttpSecurity configure( HttpSecurity sec ) throws Exception {
				
				log.info( "JWT Http 기본 설정" );
				
				sec.httpBasic().disable();
				
				return sec;
		}
		
		@Override
		public SecurityType getSecurityType() {
				return SecurityType.JWT;
		}
}
