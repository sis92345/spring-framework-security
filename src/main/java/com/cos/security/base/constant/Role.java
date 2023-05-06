package com.cos.security.base.constant;

public enum Role {
		
		ADMIN ( "admin" , "최고 관리자"),
		MANAGER ( "manager" , "메니저"),
		USER ( "user" , "사용자");
		
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
