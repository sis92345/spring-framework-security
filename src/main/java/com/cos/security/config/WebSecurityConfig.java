package com.cos.security.config;

import com.cos.security.config.security.SecurityFilterChainConfigurerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Web Security Config
 *
 * 1. 기존에는 WebSecurityConfigurerAdapter를 상속받아서 설정을 구현했으나
 * 2. 5.7.0 이후에는 SecurityFilterChain Bean을 등록해서 구현한다.
 * */
@Configuration
@EnableWebSecurity // EnableWebSecurity annotation이 있으면 SpringSecurityFilter가 스프링 필터 체인에 추가된다.
@EnableMethodSecurity( securedEnabled = true , prePostEnabled = false) // Secured , Pre/PostAuthorize Annotation 활성화
@RequiredArgsConstructor
public class WebSecurityConfig {
		
		private final SecurityFilterChainConfigurerFactory securityFilterChainConfigurerFactory;
		

		@Bean
		public SecurityFilterChain httpSecurityFilterChain ( HttpSecurity sec ) throws Exception {
				
				securityFilterChainConfigurerFactory.setSecurityConfig( sec );
				
				return sec.build();
		}
		
		/**
		 * PasswordEncode
		 * */
		@Bean
		public PasswordEncoder passwordEncoder () {
				return new BCryptPasswordEncoder();
		}
		
}
