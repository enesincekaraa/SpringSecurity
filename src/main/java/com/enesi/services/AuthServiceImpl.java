package com.enesi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.enesi.dtos.SignupRequest;
import com.enesi.dtos.UserDto;
import com.enesi.models.User;
import com.enesi.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto createUser(SignupRequest signupRequest) {
	
		User user = new User();
		user.setEmail(signupRequest.getEmail());
		user.setName(signupRequest.getName());
		user.setPhone(signupRequest.getPhone());
		user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
		User createdUser= userRepository.save(user);
		
		UserDto userDto =  new UserDto();
		userDto.setEmail(createdUser.getEmail());
		userDto.setName(createdUser.getName());
		userDto.setPhone(createdUser.getPhone());
		return userDto;
		
	}

}
