package com.cos.security.service.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Entity
@Table( name = "user" )
@Getter
@Setter
public class User {
		
		@Id
		@GeneratedValue( strategy = GenerationType.IDENTITY )
		private int id;
		private String username;
		private String password;
		private String email;
		private String role;
		
		@CreationTimestamp
		private Timestamp timestamp;
		
		
		public User () {}
		
		public boolean isEmpty () {
				return id == 0;
		}
		
		public List<String> getRoleList () {
				
				if ( !StringUtils.hasText( role ) ) {
						return Collections.emptyList();
				}
				
				return Arrays.asList( role.split( "," ) );
		}
}
