package com.ccsip.coap.master.controller;

import java.util.ArrayList;
import java.util.List;

import com.ccsip.coap.master.controller.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsip.coap.master.controller.response.ResponseCode;
import com.ccsip.coap.master.dto.AlertDto;
import com.ccsip.coap.master.utils.ConvertUtil;
import com.ccsip.coap.master.metadata.domain.confdata.DataType;
import com.ccsip.coap.master.metadata.domain.metadata.Alert;
import com.ccsip.coap.master.metadata.service.AlertService;
import com.ccsip.coap.master.metadata.service.DataTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Consuming a REST API seems simple enough, but before you can do that you must obtain authorization to access the user's resources.")
@ApiResponses(value = { @ApiResponse(code = 401, message = "NO LOGIN, NO AUTH", response = Void.class),
		@ApiResponse(code = 403, message = "NO LOGIN, NO AUTH", response = Void.class) })
@RestController
@RequestMapping("alert")
public class AlertController {
	@Autowired
	private AlertService alertService;

	@Autowired
	private DataTypeService dataTypeService;

	@ApiOperation(value = "List the specified Alerts by DataTypeId", notes = "List the specified Alerts by DataTypeId (Response modelType: List->Alert)", httpMethod = "GET", produces = "application/json")
	@RequestMapping(value = "/datatype/{dataTypeId}", method = RequestMethod.GET)
	public Response listAlertByDataType(@PathVariable Long dataTypeId) {
		DataType dataType = dataTypeService.findOne(dataTypeId);
		if (dataType != null) {
			List<Alert> list = alertService.findByDataType(dataType);
			List<AlertDto> result = new  ArrayList<AlertDto>();
			for(Alert alert : list){
				result.add( ConvertUtil.extract(alert));
			}
			if (result != null && !result.isEmpty()) {
				return new Response(ResponseCode.SUCCESS.getCode(), result, "List->AlertDto");
			} else {
				return new Response(ResponseCode.LIST_EMPTY.getCode(), ResponseCode.LIST_EMPTY.getDesc());
			}
		}
		return new Response(ResponseCode.SYS_0003.getCode(), ResponseCode.SYS_0003.getDesc());
	}
}
