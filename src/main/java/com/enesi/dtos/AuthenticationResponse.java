package com.enesi.dtos;

public class AuthenticationResponse {
	
	private String jwt;
	

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public AuthenticationResponse(String jwt) {
		super();
		this.jwt = jwt;
	}
	
	

}
