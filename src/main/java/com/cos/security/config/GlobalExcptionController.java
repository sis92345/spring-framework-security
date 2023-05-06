package com.cos.security.config;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class GlobalExcptionController {
		
		@ExceptionHandler( RuntimeException.class )
		public ResponseEntity<?> runtimeException ( final RuntimeException exception ) {
				
				return ResponseEntity.badRequest().body( exception.getMessage() );
		}
}
