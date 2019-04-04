package com.ccsip.coap.master.controller;

import javax.servlet.http.HttpServletRequest;

import com.ccsip.coap.master.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccsip.coap.master.domain.Token;
import com.ccsip.coap.master.service.ITokenService;
import com.ccsip.coap.master.service.IUserService;

public class Resources {

	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected IUserService userService;

	@Autowired
	protected ITokenService tokenService;

	@Autowired
	protected RuleRepository ruleRepository;

	/**
	 * 
	 * @return 0 ->no accessToken, 1 ->has logged in, 2 -> no login
	 */
	protected int checkAccessToken() {
		return checkAccessToken(request.getHeader("accessToken"));
	}

	/**
	 * 
	 * @return 0 ->no accessToken, 1 ->has logged in, 2 -> no login
	 */
	protected int checkAccessToken(String accessToken) {
		if (accessToken == null || "null".equals(accessToken.trim().toLowerCase()) || "".equals(accessToken.trim())) {
			return 0;
		}
		Token t = tokenService.findByPublicKey(accessToken);
		if (t != null) {
			if (t.getIsVerified() == 1) {
				return 1;
			} else {
				return 2;
			}
		} else {
			return 0;
		}
	}

}
