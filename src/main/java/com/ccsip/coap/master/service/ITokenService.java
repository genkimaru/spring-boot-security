package com.ccsip.coap.master.service;

import com.ccsip.coap.master.domain.Token;
import com.ccsip.coap.master.domain.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface ITokenService {
	Token saveOrUpdateToken(Token t);

	Token findByPublicKey(String tokenKey);

	String getUsernameFromToken(String authToken);

	boolean validateToken(String authToken, UserDetails userDetails);

	void login(User u);
}
