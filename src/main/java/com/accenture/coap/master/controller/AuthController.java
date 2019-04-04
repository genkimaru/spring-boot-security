package com.accenture.coap.master.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.coap.master.controller.response.Response;
import com.accenture.coap.master.controller.response.ResponseCode;
import com.accenture.coap.master.domain.Token;
import com.accenture.coap.master.security.PasswordEncryption;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Consuming a REST API seems simple enough, but before you can do that you must obtain authorization to access the user's resources.")
@RestController
@RequestMapping("api")
public class AuthController extends Resources {

	@ApiOperation(value = "", notes = "get accessToken (Response modelType: Token)")
	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	Response getToken() {
		Token t = new Token();
		try {
			String[] pp = PasswordEncryption.getRSAKeys();
			t.setAccessToken(pp[0]);
			t.setPublicKey(pp[0]);
			t.setPrivateKey(pp[1]);
			tokenService.saveOrUpdateToken(t);
			t.setId(null);
			t.setPrivateKey(null);
			t.setPublicKey(null);
			return new Response(ResponseCode.SUCCESS.getCode(), t, "Token");
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ResponseCode.UNKNOWN.getCode());
		}
	}

	@ApiOperation(value = "", notes = "check accessToken (Response modelType: Token)")
	@RequestMapping(value = "/checkToken/{accessToken}", method = RequestMethod.GET)
	Response checkToken(@PathVariable String accessToken) {
		int check = checkAccessToken();
		if (check == 0) {
			return new Response(ResponseCode.SYS_0001.getCode());
		}
		Token t = new Token();
		t = tokenService.findByPublicKey(accessToken);
		if (t != null && t.getPrivateKey() != null) {
			t.setId(null);
			t.setPrivateKey(null);
			t.setPublicKey(null);
		}
		return new Response(ResponseCode.SUCCESS.getCode(), t, "Token");
	}

}