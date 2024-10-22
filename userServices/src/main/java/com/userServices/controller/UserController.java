package com.userServices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userServices.api.UsersApi;
import com.userServices.model.AccRegisterRequest;
import com.userServices.model.LoginRequest;
import com.userServices.service.UserService;
import com.userServices.utils.ErrorResponse;



@Controller
@RestController
public class UserController implements UsersApi {

	@Autowired(required = false)
	private UserService userService;
	
	@Override
	@RequestMapping("/users/login")
	public ResponseEntity loginUser(LoginRequest body) {
		Object LoginResponse = userService.loginServ(body);
		 if (LoginResponse instanceof ErrorResponse) {
			 ErrorResponse errorResponse = (ErrorResponse) LoginResponse;
	            return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
		 }else {
				return new ResponseEntity<Object>(LoginResponse, HttpStatus.OK);
		 }
	}

	@Override
	@RequestMapping("/users/register")
	public ResponseEntity registerUser(AccRegisterRequest body) {
		
        Object serviceResponse = userService.regService(body);

        if (serviceResponse instanceof ErrorResponse) {
            ErrorResponse errorResponse = (ErrorResponse) serviceResponse;
            return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
        } 
        else {
            return new ResponseEntity<>(serviceResponse, HttpStatus.CREATED);
        }
    }
	
	@Transactional
	@Override
	@RequestMapping("/users/deleteByEmail")
	public ResponseEntity deleteUserByEmail(String email) {
		System.out.println("_____________________"+email);
		HttpStatus status = userService.deleteAcc(email);
		if(status.equals(HttpStatus.OK)) {
			 return new ResponseEntity<>( HttpStatus.OK);
		}
		else {
			 return new ResponseEntity<>( HttpStatus.CONFLICT);

		}
		
	}

}
