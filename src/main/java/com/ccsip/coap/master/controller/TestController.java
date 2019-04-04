package com.ccsip.coap.master.controller;

import java.util.ArrayList;
import java.util.List;

import com.ccsip.coap.master.controller.response.Response;
import com.ccsip.coap.master.controller.response.ResponseCode;
import com.ccsip.coap.master.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsip.coap.master.domain.User;
import com.ccsip.coap.master.dto.*;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "NO LOGIN, NO AUTH", response = Void.class),
		@ApiResponse(code = 403, message = "NO LOGIN, NO AUTH", response = Void.class) })
@RestController
@RequestMapping(value = "test")
public class TestController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/", method = RequestMethod.POST)
    Response test(@RequestBody AlertDto param1, @RequestBody AlertGroupDto param2,
                  @RequestBody AlertingStrategyDto param3, @RequestBody ComponentDto param4,
                  @RequestBody CriticalAlertingStrategyDto param5, @RequestBody DataTypeDto param6,
                  @RequestBody DeleteUserDto param7, @RequestBody LoginDto param8, @RequestBody LogoutDto param9,
                  @RequestBody ServerDto param10, @RequestBody StatisticalAlertingStrategyDto param11,
                  @RequestBody UserInfoDto param12, @RequestBody AlertSourceDto param14, @RequestBody AppDto param15,
                  @RequestBody AppListDto param16) throws Exception {

		return new Response(ResponseCode.SUCCESS.getCode(), "Test!!!", "User");
	}

	@RequestMapping(value = "/test1/", method = RequestMethod.POST)
	Response test1(@RequestBody String data) throws Exception {
		data = java.net.URLDecoder.decode(data, "UTF-8");
		logger.info(data);
		return new Response(ResponseCode.SUCCESS.getCode(), new User(), "User");
	}

	@RequestMapping(value = "/test2/", method = RequestMethod.GET)
	Response test2() throws Exception {
		List<User> list = new ArrayList<User>();
		list.add(new User());
		return new Response(ResponseCode.SUCCESS.getCode(), list, "List->User");
	}

	@RequestMapping(value = "/test3/", method = RequestMethod.GET)
	Response test3() throws Exception {
		List<User> list = new ArrayList<User>();
		return new Response(ResponseCode.LIST_EMPTY.getCode(), list, "List->User");
	}

	@RequestMapping(value = "/test4/", method = RequestMethod.GET)
	User test4() throws Exception {
		List<User> list = new ArrayList<User>();
		return new User();
	}
}
