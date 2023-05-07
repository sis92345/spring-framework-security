package com.cos.security.base.constant;

import jakarta.servlet.http.HttpServletRequest;

public enum Header {
		
		AUTHORIZATION ( "Authorization" )
		
		;
		
		private String header;
		
		Header( String header ) {
				this.header = header;
		}
		
		public String getHeaderName() {
				return header;
		}
		
		public String getHeader ( HttpServletRequest req ) {
				return req.getHeader( header );
		}
}
