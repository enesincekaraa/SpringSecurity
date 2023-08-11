package com.enesi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.enesi.dtos.AuthenticationRequest;
import com.enesi.dtos.AuthenticationResponse;
import com.enesi.services.jwt.UserDetailsServicesImpl;
import com.enesi.utils.JwtUtil;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController {


    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserDetailsServicesImpl  detailsServicesImpl;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @PostMapping("/authentication")
    public AuthenticationResponse createAuthenticationToken
    (@RequestBody AuthenticationRequest authenticationRequest,HttpServletResponse response)throws BadCredentialsException,DisabledException,UsernameNotFoundException,IOException, java.io.IOException
    {
    	try {
    		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPasssword()));
    		
    	}catch (BadCredentialsException e) {
    		
			throw  new BadCredentialsException("Incorrect Username or password");
		}catch (DisabledException disabledException) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND,"User is not created.Register User first");
			return null;
		}
    	final UserDetails details=detailsServicesImpl.loadUserByUsername(authenticationRequest.getEmail());
    	
    	final String jwt=jwtUtil.generateToken(details.getUsername());
    	return new AuthenticationResponse(jwt);
    }
}
