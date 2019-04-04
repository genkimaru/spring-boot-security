package com.ccsip.coap.master.controller;

import java.util.ArrayList;
import java.util.List;

import com.ccsip.coap.master.controller.response.Response;
import com.ccsip.coap.master.controller.response.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsip.coap.master.dto.DataTypeDto;
import com.ccsip.coap.master.utils.ConvertUtil;
import com.ccsip.coap.master.metadata.domain.confdata.DataType;
import com.ccsip.coap.master.metadata.service.DataTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Consuming a REST API seems simple enough, but before you can do that you must obtain authorization to access the user's resources.")
@RestController
@RequestMapping("datatype")
public class DataTypeController {
	@Autowired
	private DataTypeService dataTypeService;

	@ApiOperation(value = "List all DataType of the COAP Admin System", notes = "List all DataType of the COAP Admin System (Response modelType: List->DataType)", httpMethod = "GET", produces = "application/json")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Response list() {
		List<DataType> list = dataTypeService.findAll();
		List<DataTypeDto> result = new ArrayList<DataTypeDto>();
		for (DataType dt : list) {
			result.add(ConvertUtil.extract(dt));
		}
		if (list != null && !list.isEmpty())
			return new Response(ResponseCode.SUCCESS.getCode(), result, "List->DataType");
		else
			return new Response(ResponseCode.LIST_EMPTY.getCode(), ResponseCode.LIST_EMPTY.getDesc());
	}
}
