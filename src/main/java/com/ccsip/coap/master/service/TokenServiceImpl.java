package com.ccsip.coap.master.service;

import com.ccsip.coap.master.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccsip.coap.master.domain.Token;
import com.ccsip.coap.master.domain.User;

@Component("tokenService")
@Transactional
public class TokenServiceImpl implements ITokenService {
	@Autowired
	TokenRepository tokenRepository;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public Token saveOrUpdateToken(Token t) {
		tokenRepository.save(t);
		return t;
	}

	@Override
	public Token findByPublicKey(String tokenKey) {
		return tokenRepository.findByPublicKey(tokenKey);
	}

	@Override
	public String getUsernameFromToken(String authToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validateToken(String authToken, UserDetails userDetails) {
		// TODO Auto-generated method stub
		if (userDetails != null && authToken != null)
			return true;
		return false;
	}

	public void login(User u) {
		UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(u.getName(),
				u.getPassword());
		final Authentication authentication = authenticationManager.authenticate(upToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

	}
}
