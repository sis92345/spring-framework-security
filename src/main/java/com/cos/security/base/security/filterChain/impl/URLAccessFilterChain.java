package com.cos.security.base.security.filterChain.impl;

import com.cos.security.base.annotation.SecurityFilterChain;
import com.cos.security.base.constant.Role;
import com.cos.security.base.security.filterChain.SecurityFilterChainConfigurer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@SecurityFilterChain
@Slf4j
public class URLAccessFilterChain implements SecurityFilterChainConfigurer {
		
		@Override
		public HttpSecurity configure( HttpSecurity sec ) throws Exception {
				
				log.info( "URL Security 처리중" );
				
				sec.authorizeHttpRequests().requestMatchers( "/user/**" )
											 .authenticated()
											 .requestMatchers( "/manager/**" )
											 .hasAnyAuthority( Role.ADMIN.getRoleWithPrefix(), Role.MANAGER.getRoleWithPrefix() )
											 .requestMatchers( "/admin/**" )
											 .hasRole( Role.ADMIN.getRole() )
											 .requestMatchers( "/login" )
											 .permitAll()
											 .anyRequest()
											 .permitAll();
				
				return sec;
		}
}
