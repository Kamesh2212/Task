package com.userServices.repo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userServices.DTO.UserDetails;


@Repository
public interface UserRepo extends JpaRepository<UserDetails, UUID> {
    
	Optional<UserDetails> findByEmail(String email);
	
//	 userDetails findByUsername(String username);
    
  
}
