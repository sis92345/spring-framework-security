package com.cos.security.base.security.filter;

import com.cos.security.base.constant.Header;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;

import java.io.IOException;

/**
 * Servlet Filter
 * Security Filter에는 등록이 안되므로 addFilterBefore, addFilterAfter를 사용한다.
 * */
@Slf4j
public class AuthFilter implements Filter {
		
		@Override
		public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain )
					throws IOException, ServletException {
				
				log.info( "Spring Security Filter Chain에 등록한 필터가 항상 우선해서 적욛됨" );
				
				HttpServletRequest req = (HttpServletRequest) request;
				HttpServletResponse res = (HttpServletResponse) response;
				
				if ( !HttpMethod.POST.name().equals( req.getMethod() ) ) {
						chain.doFilter( req, res );
				}
				
				String auth = Header.AUTHORIZATION.getHeader( req );
				
				if ( auth == null ) {
						log.info( "Auth Fail!!! Auth Header is Empty" );
						return;
				}
				
				if ( auth.equals( "ted" ) ) {
						log.info( "Auth Success!!!!" );
						log.info( auth );
						chain.doFilter( req, res );
				}
				else {
						log.info( "Auth Fail!!! Invalid Auth Token" );
				}
		}
}
