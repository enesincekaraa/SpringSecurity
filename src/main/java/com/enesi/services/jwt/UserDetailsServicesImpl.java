package com.enesi.services.jwt;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.enesi.models.User;
import com.enesi.repository.UserRepository;

@Service
public class UserDetailsServicesImpl implements UserDetailsService {

	
	@Autowired
	private  UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findFirstByEmail(email);
		if(user == null) 
		{
			throw new UsernameNotFoundException("User not found",null);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),new ArrayList<>());
	}

}