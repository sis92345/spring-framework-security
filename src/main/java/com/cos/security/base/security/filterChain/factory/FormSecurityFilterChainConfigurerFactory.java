package com.cos.security.base.security.filterChain.factory;

import com.cos.security.base.annotation.SecurityFilterChain;
import com.cos.security.base.constant.SecurityType;
import com.cos.security.base.security.filterChain.SecurityFilterChainConfigurer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Form Security Filter Chain
 * */
@SecurityFilterChain
@Slf4j
public class FormSecurityFilterChainConfigurerFactory extends AbstractSecurityFilterChainConfigurerFactory {
		
		public FormSecurityFilterChainConfigurerFactory( ApplicationContext applicationContext ) {
				super( applicationContext );
		}
		
		@Override
		protected Map<String, SecurityFilterChainConfigurer> provideSecurityFilterChain( Map<String,SecurityFilterChainConfigurer> configurers ) {
				
				log.info( "Form 기반 Security 사용" );
				
				return configurers.entrySet().stream().filter( entry ->  SecurityType.FORM.checkType().apply( entry ) )
												  		.collect( Collectors.toMap( Map.Entry::getKey , Map.Entry::getValue ) );
		}
}
