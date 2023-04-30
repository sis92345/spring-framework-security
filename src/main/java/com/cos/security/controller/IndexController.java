package com.cos.security.controller;

import com.cos.security.service.user.UserService;
import com.cos.security.service.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Index View를 반환하는 View Controller
 *
 * @since 2023.04.30
 * @author ted
 * */
@Controller
@RequiredArgsConstructor
public class IndexController {
		
		private final UserService userService;
		
		/**
		 * 머스태치 기본 폴더는 resource / templates / .mustache
		 * */
		@GetMapping( { "" , "/" } )
		public String index () {
				return "index"; // resource / templates / index.mustache
		}
		
		@GetMapping ( "/user" )
		public @ResponseBody String user () {
				return "user";
		}
		
		@GetMapping ( "/admin" )
		public @ResponseBody String admin () {
				return "admin";
		}
		
		@GetMapping ( "/manager" )
		public @ResponseBody String manager () {
				return "manager";
		}
		
		@GetMapping ( "/loginForm" )
		public String login () {
				return "loginForm";
		}
		
		@GetMapping ( "/joinForm" )
		public String join () {
				return "joinForm";
		}
		
		/**
		 * 회원가입
		 *
		 * ** Security에서 패스워드가 암호화되어 있지 않으면 예외가 발생한다.
		 * */
		@PostMapping( "/join" )
		public @ResponseBody String joinProc ( User user ) {
				
				User result = userService.save( user );
				
				return "redirect:/loginForm";
		}
}
