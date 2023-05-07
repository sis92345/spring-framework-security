package com.cos.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Spring Security Sample
 * */
@SpringBootApplication
@ServletComponentScan
public class SpringSecurityApplication {
		
		public static void main( String[] args ) {
				SpringApplication.run( SpringSecurityApplication.class, args );
		}
		
}
