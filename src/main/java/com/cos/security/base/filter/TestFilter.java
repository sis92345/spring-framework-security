package com.cos.security.base.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import java.io.IOException;

/**
 * Servlet Filter
 * Security Filter에는 등록이 안되므로 addFilterBefore, addFilterAfter를 사용한다.
 * */
@WebFilter( urlPatterns = { "/*" } )
@Order( 0 )
@Slf4j
public class TestFilter implements Filter {
		
		@Override
		public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain )
					throws IOException, ServletException {
				
				log.info( "테스트 필터" );
				
				chain.doFilter( request, response );
		}
}
