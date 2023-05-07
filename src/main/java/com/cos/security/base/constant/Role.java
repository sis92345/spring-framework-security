package com.cos.security.base.constant;

public enum Role {
		
		ADMIN ( "ADMIN" , "최고 관리자"),
		MANAGER ( "MANAGER" , "메니저"),
		USER ( "USER" , "사용자");
		
		;
		
		private String role;
		private String description;
		
		Role( String role, String description ) {
				this.role = role;
				this.description = description;
		}
		
		public String getRoleWithPrefix () {
				return "ROLE_" + role;
		}
		
		public String getRole() {
				return role;
		}
		
		public String getDescription() {
				return description;
		}
}
