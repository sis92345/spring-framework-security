package com.cos.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Web Security Config
 *
 * 1. 기존에는 WebSecurityConfigurerAdapter를 상속받아서 설정을 구현했으나
 * 2. 5.7.0 이후에는 SecurityFilterChain Bean을 등록해서 구현한다.
 * */
@Configuration
@EnableWebSecurity // EnableWebSecurity annotation이 있으면 SpringSecurityFilter가 스프링 필터 체인에 추가된다.
public class WebSecurityConfig {

		@Bean
		public SecurityFilterChain httpSecurityFilterChain ( HttpSecurity sec ) throws Exception {
				
				sec.csrf().disable();
				
				sec.authorizeHttpRequests().requestMatchers( "/user/**" )
										   .authenticated()
										   .requestMatchers( "/manager/**" )
										   .hasAnyAuthority( "ROLE_ADMIN", "ROLE_MANAGER" )
										   .requestMatchers( "/admin/**" )
										   .hasRole( "ADMIN" )
										   .anyRequest()
										   .permitAll()
										   .and()
										   .formLogin()
							 			   .loginPage( "/loginForm" );
				
				
				return sec.build();
		}
		
		/**
		 * Password 암호화
		 * */
		@Bean
		public BCryptPasswordEncoder passwordEncoder () {
				return new BCryptPasswordEncoder();
		}
}
