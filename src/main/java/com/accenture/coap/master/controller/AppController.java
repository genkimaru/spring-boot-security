package com.accenture.coap.master.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.coap.master.assembler.AppAssembler;
import com.accenture.coap.master.controller.response.Response;
import com.accenture.coap.master.controller.response.ResponseCode;
import com.accenture.coap.master.dto.AppListDto;
import com.accenture.coap.master.metadata.domain.metadata.App;
import com.accenture.coap.master.metadata.service.AppService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Consuming a REST API seems simple enough, but before you can do that you must obtain authorization to access the user's resources.")
@ApiResponses(value = { @ApiResponse(code = 401, message = "NO LOGIN, NO AUTH", response = Void.class),
		@ApiResponse(code = 403, message = "NO LOGIN, NO AUTH", response = Void.class) })
@RestController
@RequestMapping("app")
public class AppController {
	@Autowired
	private AppService appService;

	@ApiOperation(value = "List specified Apps", notes = "List specified Apps (Response modelType: List->AppListDto)", httpMethod = "POST", produces = "application/json")
	@RequestMapping(value = "/listby/", method = RequestMethod.POST)
	public Response listBy(@RequestBody AppListDto dto) {
		final Long airId = dto.getAirId();
		final String appName = dto.getAppName();
		List<App> list = null;
		if ((airId != null && airId > 0) || !"".equals(appName.trim())) {
			list = appService.findByAirIdOrAppNameLike(airId, appName);
		} else {
			list = appService.findAll();
		}
		if (list != null && !list.isEmpty()) {
			List<AppListDto> dtoList = AppAssembler.extractAppListDTO(list);
			return new Response(ResponseCode.SUCCESS, dtoList, "List->AppListDto");
		} else {
			return new Response(ResponseCode.LIST_EMPTY);
		}
	}

	@ApiOperation(value = "find specified App", notes = "find specified App (Response modelType: App)", httpMethod = "GET", produces = "application/json")
	@RequestMapping(value = "/{airId}", method = RequestMethod.GET)
	public Response findOneApp(@PathVariable Long airId) {
		App app = appService.findByAirId(airId);
		if (app != null) {
			AppAssembler.excludeLoopLinding(app);
			return new Response(ResponseCode.SUCCESS, app, "App");
		} else {
			return new Response(ResponseCode.UNFOUND);
		}
	}

	@ApiOperation(value = "Delete the specified App", notes = "Delete the specified App (Response modelType: None)", httpMethod = "DELETE", produces = "application/json")
	@RequestMapping(value = "/{airId}", method = RequestMethod.DELETE)
	public Response delete(@PathVariable Long airId) {
		try {
			App app = appService.findByAirId(airId);
			if (app != null) {
				appService.delete(app);
				return new Response(ResponseCode.SUCCESS);
			} else {
				return new Response(ResponseCode.UNFOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ResponseCode.UNKNOWN, e);
		}
	}
}
