package com.accenture.coap.master.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.coap.master.controller.response.Response;
import com.accenture.coap.master.controller.response.ResponseCode;
import com.accenture.coap.master.domain.Token;
import com.accenture.coap.master.domain.User;
import com.accenture.coap.master.dto.DeleteUserDto;
import com.accenture.coap.master.dto.LoginDto;
import com.accenture.coap.master.dto.LogoutDto;
import com.accenture.coap.master.dto.UserInfoDto;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@ApiResponses(value = { @ApiResponse(code = 401, message = "NO LOGIN, NO AUTH", response = Void.class),
		@ApiResponse(code = 403, message = "NO LOGIN, NO AUTH", response = Void.class) })
@RestController
@RequestMapping(value = "user")
public class UserController extends Resources {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@ApiOperation(value = "List all users of the COAP Admin System", notes = "List all users of the COAP Admin System (Response modelType: List->User)", httpMethod = "GET", produces = "application/json")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	Response home() {
		Iterable<User> users = userService.findAllUser();
		if (users != null) {
			List<User> list = (List<User>) users;
			if (!list.isEmpty()) {
				return new Response(ResponseCode.SUCCESS.getCode(), list, "List->User");
			}
		}
		return new Response(ResponseCode.LIST_EMPTY.getCode());
	}

	@ApiOperation(value = "Query an User from COAP Admin System", notes = "Query an User from COAP Admin System (Response modelType: User)", httpMethod = "GET", produces = "application/json")
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	@ApiImplicitParams({ @ApiImplicitParam(name = "userId", required = true, dataType = "Long", paramType = "path") })
	Response getUserById(@PathVariable Long userId) {
		User u = new User();
		u = userService.findOneUser(userId);
		if (u != null) {
			u.setPassword(null);
			return new Response(ResponseCode.SUCCESS.getCode(), u, "User");
		} else {
			return new Response(ResponseCode.LIST_EMPTY.getCode());
		}
	}

	@ApiOperation(value = "Delete an User from COAP Admin System", notes = "Delete an User from COAP Admin System (Notes:You must be login before you can use the API,otherwise it will be give a HTTP 401 or 403 error)\n  (Response modelType: None)", httpMethod = "DELETE", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "user", required = true, dataType = "DeleteUserDto", paramType = "body") })
	// @PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	Response delete(@RequestBody DeleteUserDto user) {
		try {
			int check = checkAccessToken();
			if (check != 1) {
				return new Response(ResponseCode.SYS_0002.getCode());
			}
			Long id = user.getId();
			userService.deleteUser(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ResponseCode.UNKNOWN.getCode());
		}
		return new Response(ResponseCode.SUCCESS.getCode());
	}

	@ApiOperation(value = "register new User", notes = "register new User (Response modelType: User)", httpMethod = "POST", produces = "application/json")
	@ApiImplicitParams({ @ApiImplicitParam(name = "user", required = true, dataType = "UserInfoDto", paramType = "body") })
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Response create(@RequestBody UserInfoDto user) {
		User u = new User();
		if (user.getId() != null && user.getId() != 0) {
			int check = checkAccessToken();
			if (check == 0) {
				return new Response(ResponseCode.SYS_0001.getCode());
			}
		}
		if (userService.findUserByNameOrEmail(user.getName(), user.getEmail()) != null) {
			// The username or email already registered
			return new Response(ResponseCode.USER_1002.getCode());
		}

		u.setName(user.getName());
		u.setEmail(user.getEmail());
		u.setPhone(user.getPhone());

		if (user.getPassword() != null && !"".equals(user.getPassword())) {
			u.setPassword(user.getPassword());
		}
		userService.saveOrUpdateUser(u);
		u.setPassword(null);
		return new Response(ResponseCode.SUCCESS.getCode(), u, "User");
	}

	@ApiOperation(value = "Update user information", notes = "Update user information (Notes:You must be login before you can use the API,otherwise it will be give a HTTP 401 or 403 error)\n  (Response modelType: User)", httpMethod = "POST", produces = "application/json")
	@ApiImplicitParams({ @ApiImplicitParam(name = "user", required = true, dataType = "UserInfoDto", paramType = "body") })
	// @PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Response update(@RequestBody UserInfoDto user) {
		int check = checkAccessToken();
		if (check != 1) {
			return new Response(ResponseCode.SYS_0002.getCode());
		}
		Long userId = user.getId();
		if (userId == null || 0 == userId) {
			return new Response(ResponseCode.SYS_0003.getCode());
		}
		User u = userService.findOneUser(userId);
		u.setName(user.getName() != null ? user.getName() : null);
		u.setEmail(user.getEmail());
		u.setPhone(user.getPhone());
		if (user.getPassword() != null && !"".equals(user.getPassword())) {
			u.setPassword(user.getPassword());
		}
		userService.saveOrUpdateUser(u);
		u.setPassword(null);
		return new Response(ResponseCode.SUCCESS.getCode(), u, "User");
	}

	@ApiOperation(value = "User login API", notes = "After login successfully, the system binds the user and the accessToken key (Response modelType: User)", produces = "application/json")
	@ApiImplicitParams({ @ApiImplicitParam(name = "user", required = true, dataType = "LoginDto", paramType = "body") })
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	Response login(@RequestBody LoginDto user) throws Exception {
		int check = checkAccessToken();
		if (check == 0) {
			return new Response(ResponseCode.SYS_0001.getCode());
		}
		String accessToken = request.getHeader("accessToken");
		Token t = tokenService.findByPublicKey(accessToken);
		if (t == null || t.getPrivateKey() == null) {
			return new Response(ResponseCode.SYS_0001.getCode());
		}
		String userName = user.getUserName();
		User u = userService.findUserByName(userName);
		if (u != null && user.getPassword() != null && u.getPassword() != null
				&& u.getPassword().equals(user.getPassword())) {
			// if (t.getIsVerified() == 1 && !t.getUserId().equals(u.getId()))
			// {}
			t.setIsVerified(1);
			t.setUserId(u.getId());
			tokenService.saveOrUpdateToken(t);
			tokenService.login(u);
			t.setPrivateKey(null);
			u.setPassword(null);
			return new Response(ResponseCode.SUCCESS.getCode(), u, "User");
		} else {
			return new Response(ResponseCode.USER_1003.getCode());
		}
	}

	@ApiOperation(value = "User logout", notes = "the system will unbinds the user and the accessToken key (Notes:You must be login before you can use the API,otherwise it will be give a HTTP 401 or 403 error)\n  (Response modelType: None)", produces = "application/json")
	@ApiImplicitParams({ @ApiImplicitParam(name = "logout", required = true, dataType = "LogoutDto", paramType = "body") })
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	Response logout(@RequestBody LogoutDto logout) {
		int check = checkAccessToken();
		if (check == 0) {
			return new Response(ResponseCode.SYS_0001.getCode());
		}
		String accessToken = request.getHeader("accessToken");
		Token token = tokenService.findByPublicKey(accessToken);
		token.setIsVerified(0);
		tokenService.saveOrUpdateToken(token);
		token.setPrivateKey(null);
		return new Response(ResponseCode.SUCCESS.getCode());
	}
}
