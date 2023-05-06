package com.cos.security.config;

import com.cos.security.base.security.filterChain.SecurityFilterChainConfigurerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@RequiredArgsConstructor
public class WebSecurityConfig {
		
		private final SecurityFilterChainConfigurerFactory factory;

		@Bean
		public SecurityFilterChain httpSecurityFilterChain ( HttpSecurity sec ) throws Exception {
				
				factory.setSecurityConfig( sec );
				//sec.csrf().disable();
				
//				sec.authorizeHttpRequests().requestMatchers( "/user/**" )
//										   .authenticated()
//										   .requestMatchers( "/manager/**" )
//										   .hasAnyAuthority( "ROLE_ADMIN", "ROLE_MANAGER" )
//										   .requestMatchers( "/admin/**" )
//										   .hasRole( "ADMIN" )
//										   .anyRequest()
//										   .permitAll()
//										   .and()
//										   .formLogin()
//							 			   .loginPage( "/loginForm" )
//										   .loginProcessingUrl( "/login" ).defaultSuccessUrl( "/" ); // login 주소가 호출되면 시큐리티가 낚아채서 대신 로그인을 진행한다. -> Controller login이 필요 없다.
//
				
				return sec.build();
		}
		
		
		
		/**
		 * Password 암호화
		 * */
		@Bean
		public PasswordEncoder passwordEncoder () {
				return new BCryptPasswordEncoder();
		}
}
