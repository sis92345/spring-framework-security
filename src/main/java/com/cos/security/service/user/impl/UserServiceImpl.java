package com.cos.security.service.user.impl;

import com.cos.security.service.user.UserService;
import com.cos.security.service.user.dao.UserRepository;
import com.cos.security.service.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
		
		private final UserRepository userRepository;
		
		private final PasswordEncoder passwordEncoder;
		
		
		@Override
		public User save( User user ) {
				
				user.setRole( "ROLE_ADMIN" );
				// Security에서 패스워드가 암호화되어 있지 않으면 예외가 발생한다.
				user.setPassword( passwordEncoder.encode( user.getPassword() ) );
				return userRepository.save( user );
		}
		
		@Override
		public User getByUserName( String userName ) {
				return userRepository.getUserByUsername( userName );
		}
}
