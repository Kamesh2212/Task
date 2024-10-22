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

    public Object loginServ(LoginRequest body) {
    	
    	UserDetails u =new UserDetails();
    	u.setEmail(body.getEmail());
    	u.setPassword(body.getPassword());
    	
      UserDetails userDetailsOpt = repo.findByEmail(u.getEmail());
  

        if (userDetailsOpt != null) {
            
            if (passwordMatches(body.getPassword(), userDetailsOpt.getPassword())) {
            	String token = jwtUtil.generateToken(userDetailsOpt.getEmail());
            	InlineResponse200 response = new InlineResponse200(); 
            	System.out.println("********"+token);
            	response.setToken(token);
                return response; 
            }
        }
        else {
            return new ErrorResponse(HttpStatus.NOT_FOUND, "Please sign up with email first");
        }
        
        return new InlineResponse200(); // Adjust return type as needed
    }

    private boolean passwordMatches(String rawPassword, String storedPassword) {
        return rawPassword.equals(storedPassword); // This is just an example; use proper hashing
    }
    
    public Object regService(AccRegisterRequest reg) {
        UserDetails user = new UserDetails(reg);

        try {
            UserDetails savedUser = repo.save(user);

            if (savedUser != null) {
                AccRegisterResponse response = new AccRegisterResponse();
                response.setUid(savedUser.getUid());
                response.setFirstname(reg.getFirstname());
                response.setLastname(reg.getLastname());
                response.setEmail(reg.getEmail());
                response.setUsername(reg.getUsername());

                return response;
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to save user details.");
            }
        } catch (DataIntegrityViolationException e) {
            return new ErrorResponse(HttpStatus.CONFLICT, "A user with this email or username already exists. Please use a different one.");
        } catch (Exception e) {
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,  "An unexpected error occurred. Please try again later.");
        }
    }
    
	public HttpStatus deleteAcc(String email) {

		UserDetails userDetails = repo.findByEmail(email);
		if (userDetails != null) {
			repo.deleteByEmail(email);
			UserDetails u = repo.findByEmail(userDetails.getEmail());
			if (u == null) {
				return HttpStatus.OK;
			} else {
				return HttpStatus.CONFLICT;
			}
		} else {
			return HttpStatus.NOT_FOUND;
		}

	}
    
}
