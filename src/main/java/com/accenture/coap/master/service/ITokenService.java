package com.accenture.coap.master.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.accenture.coap.master.domain.Token;
import com.accenture.coap.master.domain.User;

public interface ITokenService {
	Token saveOrUpdateToken(Token t);

	Token findByPublicKey(String tokenKey);

	String getUsernameFromToken(String authToken);

	boolean validateToken(String authToken, UserDetails userDetails);

	void login(User u);
}
