package com.userServices.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.userServices.DTO.UserDetails;
import com.userServices.model.AccRegisterRequest;
import com.userServices.model.AccRegisterResponse;
import com.userServices.model.InlineResponse200;
import com.userServices.model.LoginRequest;
import com.userServices.repo.UserRepo;
import com.userServices.utils.ErrorResponse;
import com.userServices.utils.JwtUtil;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;
    
    @Autowired
    private JwtUtil jwtUtil;

    public InlineResponse200 loginServ(LoginRequest body) {
    	
    	UserDetails u =new UserDetails();
    	u.setEmail(body.getEmail());
    	u.setPassword(body.getPassword());
    	
        Optional<UserDetails> userDetailsOpt = repo.findByEmail(u.getEmail());
        
        
        if (userDetailsOpt.isPresent()) {
            UserDetails userDetails = userDetailsOpt.get();
            
            if (passwordMatches(body.getPassword(), userDetails.getPassword())) {
            	String token = jwtUtil.generateToken(userDetails.getEmail());
            	InlineResponse200 response = new InlineResponse200(); 
            	System.out.println("********"+token);
            	response.setToken(token);
                return response; 
            }
        }
        
        return new InlineResponse200(); // Adjust return type as needed
    }

    private boolean passwordMatches(String rawPassword, String storedPassword) {
        // Implement your password matching logic here (e.g., using BCrypt)
        return rawPassword.equals(storedPassword); // This is just an example; use proper hashing
    }
    
    public Object regService(AccRegisterRequest reg) {
        UserDetails user = new UserDetails(reg);

        try {
            // Attempt to save the UserDetails entity
            UserDetails savedUser = repo.save(user);

            if (savedUser != null) {
                // Construct the response for successful registration
                AccRegisterResponse response = new AccRegisterResponse();
                response.setUid(savedUser.getUid());
                response.setFirstname(reg.getFirstname());
                response.setLastname(reg.getLastname());
                response.setEmail(reg.getEmail());
                response.setUsername(reg.getUsername());

                // Return response object on success
                return response;
            } else {
                // This branch should not generally be reached if save() is successful
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to save user details.");
            }
        } catch (DataIntegrityViolationException e) {
            // Handle unique constraint violation (e.g., duplicate email)
            return new ErrorResponse(HttpStatus.CONFLICT, "Registration failed", "A user with this email or username already exists. Please use a different one.");
        } catch (Exception e) {
            // Handle any other exceptions
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Registration failed", "An unexpected error occurred. Please try again later.");
        }
    }
}
