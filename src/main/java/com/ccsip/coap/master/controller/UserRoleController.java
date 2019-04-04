/*package com.ccsip.coap.master.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsip.coap.master.controller.response.Response;
import com.ccsip.coap.master.domain.UserRole;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "NO LOGIN, NO AUTH", response = Void.class),
		@ApiResponse(code = 403, message = "NO LOGIN, NO AUTH", response = Void.class) })
@RestController
@RequestMapping(value = "user/role")
public class UserRoleController extends Resources {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<UserRole> list() {
		Iterable<UserRole> UserRoles = userService.findAllUserRole();
		return (List<UserRole>) UserRoles;
	}

	@ApiImplicitParams({ @ApiImplicitParam(name = "roldId", required = true, dataType = "Long", paramType = "path") })
	@RequestMapping(value = "/{roldId}", method = RequestMethod.GET)
	public UserRole getUserRoleById(@PathVariable Long roldId) {
		UserRole u = new UserRole();
		u = userService.findOneUserRole(roldId);
		return u;
	}

	@ApiImplicitParams({
			@ApiImplicitParam(name = "userRole", required = true, dataType = "UserRole", paramType = "body") })
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Response delete(@RequestBody UserRole userRole) {

		return null;
	}

	@ApiImplicitParams({
			@ApiImplicitParam(name = "userRole", required = true, dataType = "UserRole", paramType = "body") })
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public UserRole create(@RequestBody UserRole userRole) {

		return null;
	}

	@ApiImplicitParams({
			@ApiImplicitParam(name = "userRole", required = true, dataType = "UserRole", paramType = "body") })
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Response update(@RequestBody UserRole userRole) {

		return null;
	}
}
*/