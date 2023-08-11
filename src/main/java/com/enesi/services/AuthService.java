package com.enesi.services;

import com.enesi.dtos.SignupRequest;
import com.enesi.dtos.UserDto;

public interface AuthService {
	
	UserDto createUser(SignupRequest signupRequest);

}
