package com.cos.security.base.security.filterChain;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SecurityFilterChain의 규격에 맞게 설정합니다.
 * */
public interface SecurityFilterChainConfigurer {
		
		HttpSecurity configure ( HttpSecurity sec ) throws Exception;


}
