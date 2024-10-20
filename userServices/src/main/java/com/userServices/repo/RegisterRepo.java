package com.userServices.repo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RegisterRepo{
	 private final JdbcTemplate jdbcTemplate;
	 
	 public  RegisterRepo(JdbcTemplate jdbcTemplate) {
	        this.jdbcTemplate = jdbcTemplate;
	    }
	 
//	  public int save(RegisterAccRegisterRequest bdy,UUID uid) {
//		  
//		  int result = jdbcTemplate.update("INSERT INTO userDetails(uid, username, firstname, lastname, email,password,created_at) VALUES (?, ?, ?, ?, ?, ?,?)",
//				    uid,                        // UUID for the user
//				    bdy.getUsername(),           // Username
//				    bdy.getFirstname(),          // First name
//				    bdy.getLastname(),           // Last name
//				    bdy.getEmail(),              // Email
//				    bdy.getPassword(),
//				    new Timestamp(System.currentTimeMillis())  // Current timestamp for created_at
//				);
//
//	      return result;
//	        
//	    }
	  
	
}