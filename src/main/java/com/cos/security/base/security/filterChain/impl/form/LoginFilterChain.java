package com.cos.security.base.security.filterChain.impl.form;

import com.cos.security.base.annotation.SecurityFilterChain;
import com.cos.security.base.constant.SecurityType;
import com.cos.security.base.security.filterChain.SecurityFilterChainConfigurer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * Username과 Password로 인증하는 인증 필터, 이거 구현하면 자동으로 /login endpoint 생김
 * */
@SecurityFilterChain
@Slf4j
public class LoginFilterChain implements SecurityFilterChainConfigurer {
		
		
		/**
		 * Spring Security Config 설정
		 * login 주소가 호출되면 시큐리티가 낚아채서 대신 로그인을 진행한다. -> Controller login이 필요 없다.
		 * */
		@Override
		public HttpSecurity configure( HttpSecurity sec ) throws Exception {
				
				log.info( "로그인 Security 처리중" );
				
				sec.formLogin()
				   .loginPage( "/loginForm" )
				   .usernameParameter( "username" )
				   .loginProcessingUrl( "/login" )
				   .defaultSuccessUrl( "/" );
				
				return sec;
		}
		
		@Override
		public SecurityType getSecurityType() {
				return SecurityType.FORM;
		}
}
