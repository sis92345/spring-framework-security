package com.cos.security.service.user.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

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
}
