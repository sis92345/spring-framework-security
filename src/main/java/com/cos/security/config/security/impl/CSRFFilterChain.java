package com.cos.security.config.security.impl;

import com.cos.security.base.annotation.SecurityFilterChain;
import com.cos.security.base.constant.SecurityType;
import com.cos.security.config.security.SecurityFilterChainConfigurer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@SecurityFilterChain
@Slf4j
public class CSRFFilterChain implements SecurityFilterChainConfigurer {
		
		
		@Override
		public HttpSecurity configure( HttpSecurity sec ) throws Exception {
				
				log.info( "CSFR 설정" );
				
				sec.csrf().disable();
				
				return sec;
		}
		
		@Override
		public SecurityType getSecurityType() {
				return SecurityType.BOTH;
		}
}
