package com.cos.security.base.constant;

import com.cos.security.config.security.SecurityFilterChainConfigurer;

import java.util.Map;
import java.util.function.Function;

public enum SecurityType {
		
		FORM("FORM" ),
		JWT("JWT" ),
		BOTH("BOTH" )
		;
		
		private String key;
		private Function<Map.Entry<String, SecurityFilterChainConfigurer>,Boolean> getImpl;
		
		static {
				BOTH.getImpl = entry -> BOTH.isEqualsByKey( entry.getValue().getSecurityType().getKey() );
				FORM.getImpl = entry -> BOTH.isEqualsByKey( entry.getValue().getSecurityType().getKey() ) || FORM.isEqualsByKey( entry.getValue().getSecurityType().getKey() );
				JWT.getImpl  = entry -> BOTH.isEqualsByKey( entry.getValue().getSecurityType().getKey() ) || JWT.isEqualsByKey( entry.getValue().getSecurityType().getKey() );
				
		}
		
		SecurityType( String key ) {
				
				this.key = key;
		}
		
		public String getKey() {
				return key;
		}
		
		public Function<Map.Entry<String, SecurityFilterChainConfigurer>, Boolean> checkType() {
				return getImpl;
		}
		
		public boolean isEqualsByKey( String otherKey ) {
				return this.key.equals(otherKey);
		}
}
