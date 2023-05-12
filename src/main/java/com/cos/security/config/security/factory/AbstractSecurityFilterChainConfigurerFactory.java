package com.cos.security.config.security.factory;

import com.cos.security.base.annotation.SecurityFilterChain;
import com.cos.security.config.security.SecurityFilterChainConfigurer;
import com.cos.security.config.security.SecurityFilterChainConfigurerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * SecurityFilterChain 설정 객체를 만들어주는 Factory 클래스
 * 실제 Factory는 이 추상 클래스가 담당하고, 실제 관련 클래스 전달은 구현체가 담당
 * */
@SecurityFilterChain
@RequiredArgsConstructor
public abstract class AbstractSecurityFilterChainConfigurerFactory implements SecurityFilterChainConfigurerFactory {
		
		protected Map<String, SecurityFilterChainConfigurer> configurerMap = new HashMap<>();
		
		private final ApplicationContext applicationContext;
		
		@Override
		public void setSecurityConfig( HttpSecurity security ) {
				
				Map<String,SecurityFilterChainConfigurer> map = applicationContext.getBeansOfType( SecurityFilterChainConfigurer.class );
				
				configurerMap = provideSecurityFilterChain( map );
				
				if ( configurerMap == null || CollectionUtils.isEmpty( configurerMap ) ) {
						return;
				}
				
				configurerMap.forEach( ( key , item ) -> {
						
						try {
								item.configure( security );
						}
						catch ( Exception e ) {
								throw new RuntimeException( "Security Filter Chain 설정 중 문제 발생" );
						}
						
				});
		}
		
		/**
		 * 필요한 SecurityFilterChain을 반환합니다.
		 *
		 * @return SecurityFilterChainConfigurer
		 * */
		protected abstract Map<String,SecurityFilterChainConfigurer> provideSecurityFilterChain( Map<String,SecurityFilterChainConfigurer> configurers );
}
