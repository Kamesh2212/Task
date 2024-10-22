package com.userServices.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userServices.DTO.UserDetails;


@Repository
public interface UserRepo extends JpaRepository<UserDetails, UUID> {
    
	UserDetails findByEmail(String email);
	
	void deleteByEmail(String email);
	    
  
}
