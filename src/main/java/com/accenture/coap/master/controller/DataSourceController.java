package com.accenture.coap.master.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.coap.master.controller.response.Response;
import com.accenture.coap.master.controller.response.ResponseCode;
import com.accenture.coap.master.metadata.domain.confdata.DataSource;
import com.accenture.coap.master.metadata.domain.confdata.DataType;
import com.accenture.coap.master.metadata.service.AlertGroupService;
import com.accenture.coap.master.metadata.service.DataSourceService;
import com.accenture.coap.master.metadata.service.DataTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Consuming a REST API seems simple enough, but before you can do that you must obtain authorization to access the user's resources.")
@ApiResponses(value = { @ApiResponse(code = 401, message = "NO LOGIN, NO AUTH", response = Void.class),
		@ApiResponse(code = 403, message = "NO LOGIN, NO AUTH", response = Void.class) })
@RestController
@RequestMapping("datasource")
public class DataSourceController {
	@Autowired
	DataSourceService dataSourceService;
	@Autowired
	DataTypeService dataTypeService;

	@ApiOperation(value = "List all DataSource of the COAP Admin System", notes = "List all DataSource of the COAP Admin System (Response modelType: List->DataSource)", httpMethod = "GET", produces = "application/json")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Response list() {
		List<DataSource> list = dataSourceService.findAll();
		if (list != null && !list.isEmpty()) {
			return new Response(ResponseCode.SUCCESS.getCode(), list, "List->DataSource");
		} else {
			return new Response(ResponseCode.LIST_EMPTY.getCode());
		}
	}

}
