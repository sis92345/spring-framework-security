package com.cos.security.base.security.auth;

import com.cos.security.service.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 시큐리티가 login을 낚아채서 Security가 Login을 진행하면
 * 로그인 정보를 Security Session 영역에 넣는다 이를 Security Context다
 * 이때 들어갈 수 있는 객체는 Authentication이고 이 안에 User Detail을 가지고 있다.
 * 즉 Secutiry Session => Authntication => User Detail
 * 이 클래스는 User Detail를 상속한 것
 * */
@RequiredArgsConstructor
@Getter
public class PrincipalDetails implements UserDetails {
		
		private final User user; // Composition
		
		/**
		 * User의 권한을 반환한다.
		 * */
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
				
				Collection<GrantedAuthority> collect = new ArrayList<>();
				collect.add( (GrantedAuthority) user::getRole );
				
				return collect;
		}
		
		@Override
		public String getPassword() {
				return user.getPassword();
		}
		
		@Override
		public String getUsername() {
				return user.getUsername();
		}
		
		@Override
		public boolean isAccountNonExpired() {
				return true;
		}
		
		/**
		 * User가 잠긴 상태인지 판별합니다.
		 * */
		@Override
		public boolean isAccountNonLocked() {
				return true;
		}
		
		@Override
		public boolean isCredentialsNonExpired() {
				return true;
		}
		
		@Override
		public boolean isEnabled() {
				return true;
		}
}
