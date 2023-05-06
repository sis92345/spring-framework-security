package com.cos.security.base.security.filterChain.impl;

import com.cos.security.base.annotation.SecurityFilterChain;
import com.cos.security.base.security.filterChain.SecurityFilterChainConfigurer;
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
}
