package com.cos.security.config.security.impl.jwt;

import com.cos.security.base.constant.Role;
import com.cos.security.base.constant.SecurityType;
import com.cos.security.config.security.SecurityFilterChainConfigurer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CorsFilter;

@Component
@RequiredArgsConstructor
@Slf4j
public class JWTAPIAccessFilterChain implements SecurityFilterChainConfigurer {
		
		private final CorsFilter corsFilter;
		
		@Override
		public HttpSecurity configure( HttpSecurity sec ) throws Exception {
				
				log.info( "JWT API 설정" );
				
				sec.authorizeHttpRequests().requestMatchers( "/api/v1/user/**" )
										   .hasAnyRole( Role.ADMIN.getRole(), Role.MANAGER.getRole(), Role.USER.getRole() )
										   .requestMatchers( "/api/v1/manager/**" )
										   .hasAnyRole( Role.ADMIN.getRole(), Role.MANAGER.getRole())
										   .requestMatchers( "/api/v1/admin/**" )
										   .hasAnyRole( Role.ADMIN.getRole() )
										   .anyRequest()
										   .permitAll();
				
				sec.addFilter( corsFilter );
				
				return sec;
		}
		
		@Override
		public SecurityType getSecurityType() {
				return SecurityType.JWT;
		}
}
