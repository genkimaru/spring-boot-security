package com.accenture.coap.master.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.coap.master.controller.response.Response;
import com.accenture.coap.master.controller.response.ResponseCode;
import com.accenture.coap.master.dto.DataTypeDto;
import com.accenture.coap.master.utils.ConvertUtil;
import com.accenture.coap.master.metadata.domain.confdata.AlertGroup;
import com.accenture.coap.master.metadata.domain.confdata.DataType;
import com.accenture.coap.master.metadata.service.DataTypeService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.PropertyWriter;

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
