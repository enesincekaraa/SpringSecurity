package com.enesi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.enesi.dtos.SignupRequest;
import com.enesi.dtos.UserDto;
import com.enesi.services.AuthService;

@RestController
public class SignupUserController {
	
	@Autowired
	private AuthService  authService;
	
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@RequestBody SignupRequest signupRequest)
	{
		UserDto createdUser=authService.createUser(signupRequest);
		if(createdUser==null) {
			return new ResponseEntity<>("User is Not Created",HttpStatus.BAD_REQUEST);
		
		}
		return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
	}

}
