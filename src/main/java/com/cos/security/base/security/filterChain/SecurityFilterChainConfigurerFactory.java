package com.cos.security.base.security.filterChain;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * 설정된 SecurityFilterChain을 설정을 모아서 반환시킵니다.
 *
 * @ TODO abstrract Factory 추가 필요
 * */
public interface SecurityFilterChainConfigurerFactory {
		
		/**
		 * SecurityFilterChainConfigurer를 모두 실행해서 FilterChain을 생성합니다.
		 * */
		void setSecurityConfig ( HttpSecurity security );
}
