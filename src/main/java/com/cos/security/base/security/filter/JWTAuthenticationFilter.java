package com.cos.security.base.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cos.security.base.constant.Header;
import com.cos.security.base.security.auth.PrincipalDetails;
import com.cos.security.service.user.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;

/**
 * 기존 Username, Password 인증 기반 인증 구현, 단 JWT를 사용 + /login url 으로 처리
 * */
@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
		
		private final AuthenticationManager authenticationManager;
		
		private static final String JWT_SECRET_KEY = "TED";
		
		public JWTAuthenticationFilter( AuthenticationManager authenticationManager ) {
				this.authenticationManager = authenticationManager;
		}
		
		@Override
		public Authentication attemptAuthentication( HttpServletRequest request, HttpServletResponse response )
					throws AuthenticationException {
				
				System.out.println( "로그인 시도 중" );
				
				// 1. 로그인 데이터 받기
				ObjectMapper om = new ObjectMapper();
				User user = null;
				try {
						user = om.readValue( request.getInputStream(), User.class );
				}
				catch ( IOException e ) {
						throw new RuntimeException( e );
				}
				
				UsernamePasswordAuthenticationToken token =
							new UsernamePasswordAuthenticationToken( user.getUsername(), user.getPassword() );
				
				// 2. authorizationManager로 로그인 시도 -> userDetailService 실행
				// 이때 PincipalDetailServeice loadUsernameandPassword 실행
				
				// 3. 결과를 PrincipalDetails에 담고
				// 4. JWT 토큰 만들어서 처리
				
				// return 시키면 Security가 Session에 저장, 권한 처리 때문에 필수
				return authenticationManager.authenticate( token );
		}
		
		/**
		 * 인증 성공시 호출되는 메소드
		 * */
		@Override
		protected void successfulAuthentication( HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult )
					throws IOException, ServletException {
				
				PrincipalDetails principal = (PrincipalDetails) authResult.getPrincipal();
				User user = principal.getUser();
				
				log.info( "로그인한 사용자 정보 : {}" , user.getUsername() );
				
				String jwt = JWT.create().withSubject( user.getUsername() )
										 .withExpiresAt( new Date( System.currentTimeMillis() + ( 60000 * 10 )) )
										 .withClaim( "username" , user.getUsername() )
										 .withClaim( "email" , user.getEmail() )
										 .sign( Algorithm.HMAC512( JWT_SECRET_KEY ) );
				
				response.addHeader( Header.AUTHORIZATION.getHeaderName() , jwt );
		}
}
