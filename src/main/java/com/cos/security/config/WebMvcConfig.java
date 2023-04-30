package com.cos.security.config;

import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Server Side Rendring을 위한 WebMvcConfig
 * Mustache의 디폴드 view Resolve인 /resource + ".mustache" 를 html로 사용하기 위해 사용
 * */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
		
		private static final String DEFAULT_CHARSET = "UTF-8";
		
		private static final String HTML_CONTENT = "text/html; charset=UTF-8";
		private static final String VIEW_TEMPLATE_PATH = "classpath:templates/";
		private static final String VIEW_TEMPLATE_SUFFIX = ".html";
		
		/**
		 * Mustache View Resolver 변경
		 * 1. utf-8
		 * 2. html 파일을 mustache로 인식
		 * */
		@Override
		public void configureViewResolvers( ViewResolverRegistry registry ) {
				MustacheViewResolver viewResolver = new MustacheViewResolver();
				viewResolver.setCharset( DEFAULT_CHARSET );
				viewResolver.setContentType( HTML_CONTENT );
				viewResolver.setPrefix( VIEW_TEMPLATE_PATH );
				viewResolver.setSuffix( VIEW_TEMPLATE_SUFFIX );
				
				registry.viewResolver( viewResolver );
		}
}
