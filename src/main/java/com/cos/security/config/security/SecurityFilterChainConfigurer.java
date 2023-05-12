package com.cos.security.config.security;

import com.cos.security.base.constant.SecurityType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * SecurityFilterChain의 규격에 맞게 설정합니다.
 * */
public interface SecurityFilterChainConfigurer {
		
		HttpSecurity configure ( HttpSecurity sec ) throws Exception;
		
		SecurityType getSecurityType ();
}
