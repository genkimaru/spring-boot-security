package com.accenture.coap.master.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.coap.master.controller.response.Response;
import com.accenture.coap.master.controller.response.ResponseCode;
import com.accenture.coap.master.dto.AlertGroupDto;
import com.accenture.coap.master.utils.ConvertUtil;
import com.accenture.coap.master.metadata.domain.confdata.AlertGroup;
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
@RequestMapping("alertGroup")
public class AlertGroupController {
	@Autowired
	AlertGroupService alertGroupService;
	@Autowired
	DataTypeService dataTypeService;
	@Autowired
	DataSourceService dataSourceService;

	@ApiOperation(value = "List all AlertGroup of the COAP Admin System", notes = "List all AlertGroup of the COAP Admin System (Response modelType: List->AlertGroupDto)", httpMethod = "GET", produces = "application/json")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Response list() {
		List<AlertGroup> list = alertGroupService.findAll();
		// convert from domain to dto
		ArrayList<AlertGroupDto> result = new ArrayList<AlertGroupDto>();
		for (AlertGroup ag : list) {
			AlertGroupDto alertGroupDto = ConvertUtil.extract(ag);
			result.add(alertGroupDto);
		}
		if (list != null && !list.isEmpty()) {
			return new Response(ResponseCode.SUCCESS.getCode(), result, "List->AlertGroupDto");
		} else {
			return new Response(ResponseCode.LIST_EMPTY.getCode(), ResponseCode.LIST_EMPTY.getDesc());
		}

	}

	@ApiOperation(value = "List AlertGroup by Condition", notes = "List AlertGroup by Condition (Response modelType: List->AlertGroupDto)", httpMethod = "POST", produces = "application/json")
	@RequestMapping(value = "/listby/", method = RequestMethod.POST)
	public Response listBy(@RequestBody AlertGroupDto dto) {
		// from dto to domain
		AlertGroup alertGroup = ConvertUtil.assemble(dto);
		List<AlertGroup> alertGroups = alertGroupService.findByDataTypeIdAndName(alertGroup);
		ArrayList<AlertGroupDto> result = new ArrayList<AlertGroupDto>();
		for (AlertGroup ag : alertGroups) {
			result.add(ConvertUtil.extract(ag));
		}
		if (result != null && !result.isEmpty()) {
			return new Response(ResponseCode.SUCCESS.getCode(), result, "List->AlertGroupDto");
		} else {
			return new Response(ResponseCode.LIST_EMPTY.getCode(), ResponseCode.LIST_EMPTY.getDesc());
		}
	}

	@ApiOperation(value = "List AlertGroup by dataSource Id", notes = "List AlertGroup by dataSource Id (Response modelType: List->AlertGroupDto)", httpMethod = "GET", produces = "application/json")
	@RequestMapping(value = "/datasource/{dataSourceId}", method = RequestMethod.GET)
	public Response listByDataSource(@PathVariable Long dataSourceId) {
		DataSource dataSource = dataSourceService.findOne(dataSourceId);
		if (dataSource != null) {
			ArrayList<AlertGroupDto> result = new ArrayList<AlertGroupDto>();
//			List<DataType> dataTypes = dataSource.getDataTypes();
//			List<AlertGroup> alertGroups = new ArrayList<AlertGroup>();
//			for (DataType dt : dataTypes) {
//				List<AlertGroup> ag = dt.getAlertGroups();
//				alertGroups.addAll(ag);
//			}
//			// from domain to dto
//			ArrayList<AlertGroupDto> result = new ArrayList<AlertGroupDto>();
//			for (AlertGroup ag : alertGroups) {
//				result.add(ConvertUtil.extract(ag));
//			}
			return new Response(ResponseCode.SUCCESS.getCode(), result, "List->AlertGroupDto");
		} else {
			return new Response(ResponseCode.LIST_EMPTY.getCode(), ResponseCode.LIST_EMPTY.getDesc());
		}

	}

	@ApiOperation(value = "Create an AlertGroup", notes = "Create an AlertGroup (Response modelType: AlertGroup)", httpMethod = "POST", produces = "application/json")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Response create(@RequestBody AlertGroupDto dto) {
		AlertGroup alertGroup = ConvertUtil.assemble(dto);
		AlertGroup savedAlertGroup = alertGroupService.save(alertGroup);
		if (savedAlertGroup != null && savedAlertGroup.getId() > 0) {
			AlertGroupDto result = ConvertUtil.extract(savedAlertGroup);
			return new Response(ResponseCode.SUCCESS.getCode(), result, "AlertGroup");
		} else if(savedAlertGroup == null ){
			return new Response(ResponseCode.ALERT_4002.getCode(), ResponseCode.ALERT_4002.getDesc());
		}else{
			return new Response(ResponseCode.UNKNOWN.getCode(), ResponseCode.UNKNOWN.getDesc());
		}
	}

	@ApiOperation(value = "Update the specified AlertGroup of the COAP Admin System", notes = "Update the specified AlertGroup of the COAP Admin System (Response modelType: None)", httpMethod = "PUT", produces = "application/json")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Response update(@RequestBody AlertGroupDto dto) {

		if (dto.getAlertGroupId() == null || dto.getAlertGroupId() == 0) {
			return new Response(ResponseCode.SYS_0003.getCode(), ResponseCode.SYS_0003.getDesc());
		} else {
			AlertGroup alertGroup = ConvertUtil.assemble(dto);
			alertGroup.setId(dto.getAlertGroupId());
			AlertGroup update = alertGroupService.update(alertGroup);
			if(update != null ){
				return new Response(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc());
			}else{
				return new Response(ResponseCode.ALERT_4002.getCode(), ResponseCode.ALERT_4002.getDesc());
			}
		}
	}

	@ApiOperation(value = "Get the specified AlertGroup from the COAP Admin System", notes = "Get the specified AlertGroup from the COAP Admin System (Response modelType: AlertGroup)", httpMethod = "GET", produces = "application/json")
	@RequestMapping(value = "/{agId}", method = RequestMethod.GET)
	public Response retrieve(@PathVariable Long agId) {
		AlertGroup ag = alertGroupService.findOne(agId);
		if (ag != null) {
			AlertGroupDto result = ConvertUtil.extract(ag);
			return new Response(ResponseCode.SUCCESS.getCode(), result, "AlertGroup");
		} else
			return new Response(ResponseCode.LIST_EMPTY.getCode(), ResponseCode.LIST_EMPTY.getDesc());
	}

	@ApiOperation(value = "Delete the specified AlertGroup of the COAP Admin System", notes = "Delete the specified AlertGroup of the COAP Admin System (Response modelType: None)", httpMethod = "DELETE", produces = "application/json")
	@RequestMapping(value = "/{agId}", method = RequestMethod.DELETE)
	public Response delete(@PathVariable Long agId) {
		try {
			// AlertGroup ag = alertGroupService.findOne(agId);
			if (agId != null) {
				long returnCode = alertGroupService.deleteById(agId);
				if(returnCode == 0){
					return new Response(ResponseCode.IN_USE.getCode(), ResponseCode.IN_USE.getDesc());
				}else{
					return new Response(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc());
				}
			} else {
				return new Response(ResponseCode.ALERT_4001.getCode(), ResponseCode.ALERT_4001.getDesc());
			}
		} catch (Exception e) {
			return new Response(ResponseCode.UNKNOWN.getCode(), e);
		}

	}
}