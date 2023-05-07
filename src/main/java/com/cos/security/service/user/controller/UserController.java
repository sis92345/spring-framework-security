package com.cos.security.service.user.controller;

import com.cos.security.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
		
		private final UserService userService;
		
		@GetMapping( "/info" )
		@Secured( "ROLE_ADMIN" )
		public ResponseEntity<?> info () {
				
				return ResponseEntity.ok( "개인정보" );
		}
		
		@GetMapping( "/server" )
		@PreAuthorize( "hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')" )
		public ResponseEntity<?> server () {
				
				return ResponseEntity.ok( "개인정보" );
		}
		
		@GetMapping( "/client" )
		@PostAuthorize( "hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')" )
		public ResponseEntity<?> client () {
				
				return ResponseEntity.ok( "개인정보" );
		}
}
