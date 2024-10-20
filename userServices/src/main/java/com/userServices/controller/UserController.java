package com.userServices.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
		return new ResponseEntity<Object>(userService.loginServ(body), HttpStatus.OK);
	}

	@Override
	@RequestMapping("/users/register")
	public ResponseEntity registerUser(AccRegisterRequest body) {
		System.out.println("******************"+body.getEmail());
		System.out.println("******************"+body.getFirstname());
		System.out.println("******************"+body.getLastname());
		System.out.println("******************"+body.getPassword());
		System.out.println("******************"+body.getUsername());

		
        Object serviceResponse = userService.regService(body);

        if (serviceResponse instanceof ErrorResponse) {
            ErrorResponse errorResponse = (ErrorResponse) serviceResponse;
            return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
        } 
        else {
            return new ResponseEntity<>(serviceResponse, HttpStatus.CREATED);
        }
    }
	
	
	@Override
	@RequestMapping("/users/{id}")
	public ResponseEntity<Void> deleteUser(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

}
