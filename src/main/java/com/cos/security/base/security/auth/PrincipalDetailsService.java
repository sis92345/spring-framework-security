package com.cos.security.base.security.auth;

import com.cos.security.service.user.UserService;
import com.cos.security.service.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Security 설정에서 login Proccess Url 요청이 오면 자동으로 UserDetailService 타입으로 IoC 되어 있는 loadUserByUsername 실행
 * */
@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalDetailsService implements UserDetailsService {
		
		private final UserService userService;
		
		/**
		 * 파라메터 기본 설정은 username임, frond에서 username으로 요청해야함
		 * 만약 변경이 필요하면 usernameParameter 서렁을 변경하면 된다.
		 * 설정을 완료하면 Authtication으로 들어간다.
		 * */
		@Override
		public UserDetails loadUserByUsername( String userId ) throws UsernameNotFoundException {
				
				User user = userService.getByUserName( userId );
				
				if ( user == null ) {
						return null;
				}
				
				log.info( "로그인 성공!!!!" );
				
				return new PrincipalDetails( user );
		}
}
