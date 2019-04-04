package com.accenture.coap.master.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.coap.master.assembler.AlertSourceAssembler;
import com.accenture.coap.master.controller.response.Response;
import com.accenture.coap.master.controller.response.ResponseCode;
import com.accenture.coap.master.dto.AlertListOnAlertSourceEditPage;
import com.accenture.coap.master.dto.AlertSourceExt;
import com.accenture.coap.master.dto.AlertSourceFormDto;
import com.accenture.coap.master.metadata.domain.confdata.AlertGroup;
import com.accenture.coap.master.metadata.domain.confdata.AlertSource;
import com.accenture.coap.master.metadata.domain.confdata.CriticalAlertList;
import com.accenture.coap.master.metadata.domain.confdata.DataType;
import com.accenture.coap.master.metadata.domain.confdata.InclusionList;
import com.accenture.coap.master.metadata.domain.metadata.Alert;
import com.accenture.coap.master.metadata.service.AlertGroupService;
import com.accenture.coap.master.metadata.service.AlertService;
import com.accenture.coap.master.metadata.service.AlertSourceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Consuming a REST API seems simple enough, but before you can do that you must obtain authorization to access the user's resources.")
@ApiResponses(value = { @ApiResponse(code = 401, message = "NO LOGIN, NO AUTH", response = Void.class),
		@ApiResponse(code = 403, message = "NO LOGIN, NO AUTH", response = Void.class) })
@RestController
@RequestMapping("alertSource")
public class AlertSourceController {
	@Autowired
	private AlertSourceService alertSourceService;
	@Autowired
	private AlertGroupService alertGroupService;
	@Autowired
	private AlertService alertService;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@ApiOperation(value = "", notes = "Response modelType: List->(DataType,AlertSource,Status)", produces = "application/json")
	@RequestMapping(value = "/AIRID/{airid}", method = RequestMethod.GET)
	public Response initAlertSource(@PathVariable Long airid) {
		List<AlertSource> list = alertSourceService.initAlertSource(airid);
		if (list != null && !list.isEmpty()) {
			List<AlertSourceExt> extList = new ArrayList<AlertSourceExt>();
			for (AlertSource as : list) {
				AlertSourceExt ext = new AlertSourceExt();
				AlertSourceAssembler.excludeLoopLinding(as);
				ext.setStatus(as.getStatus());
				ext.setAlertSource(as);
				ext.setDataType(as.getDataType());
				extList.add(ext);
			}
			return new Response(ResponseCode.SUCCESS, extList, "List->(DataType,AlertSource,Status)");
		} else {
			return new Response(ResponseCode.LIST_EMPTY);
		}
	}

	@ApiOperation(value = "", notes = "Response modelType: None", produces = "application/json")
	@RequestMapping(value = "/AIRID/{airid}/{status}", method = RequestMethod.POST)
	public Response updateStatus(@PathVariable Long airid, @PathVariable String status) {
		try {//Effective or Inactive
			if (status == null || !"Effective".equals(status) || !"Inactive".equals(status)) {
				logger.info("The AlertSource's Status should be Effective or Inactive");
				return new Response(ResponseCode.UNKNOWN);
			}
			List<AlertSource> list = alertSourceService.findByAirId(airid);
			if (list != null) {
				for (AlertSource as : list) {
					as.setStatus(status);
					alertSourceService.update(as);
				}
			}
			return new Response(ResponseCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ResponseCode.UNKNOWN, e);
		}
	}

	@ApiOperation(value = "", notes = "Response modelType: AlertSource", produces = "application/json")
	@RequestMapping(value = "/{asId}", method = RequestMethod.GET)
	public Response view(@PathVariable Long asId) {
		AlertSource as = alertSourceService.findOne(asId);
		if (as != null) {
			as.setParent(null);
			as.setChildren(null);
			return new Response(ResponseCode.SUCCESS, as, "AlertSource");
		} else {
			return new Response(ResponseCode.UNFOUND);
		}
	}

	@ApiOperation(value = "", notes = "Response modelType: None", produces = "application/json")
	@RequestMapping(value = "/{asId}", method = RequestMethod.POST)
	public Response save(@RequestBody AlertSourceFormDto as) {
		try {
			alertSourceService.update(as);
			return new Response(ResponseCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ResponseCode.UNKNOWN, e);
		}
	}

	private void getTopOfTheASTree(AlertSource as) {
		AlertSource parent = as.getParent();
		if (parent != null) {
			getTopOfTheASTree(parent);
		}
	}

	private void getAlertOnAlertSourceTree(Set<AlertGroup> ag, Set<Alert> alt, AlertSource as) throws Exception {
		if (ag == null || alt == null) {
			throw new Exception("Set<AlertGroup> ag or Set<Alert> alt is Null");
		}
		// The top of the tree
		CriticalAlertList cal = as.getCriticalAlertList();
		if (cal instanceof InclusionList) {
			ag.add(cal.getAlertGroup());
			alt.addAll(cal.getAdditionalAlerts());
			Set<AlertSource> children = as.getChildren();
			for (AlertSource child : children) {
				getAlertOnAlertSourceTree(ag, alt, child);
			}
		}
	}

	@ApiOperation(value = "", notes = "Response modelType: List->AlertGroup,List->Alert", produces = "application/json")
	@RequestMapping(value = "/alertGroup/{asId}", method = RequestMethod.GET)
	public Response getAllAlertGroup(@PathVariable Long asId) {
		try {
			AlertSource as = alertSourceService.findOne(asId);
			DataType dataType = as.getDataType();
			CriticalAlertList cal = as.getCriticalAlertList();
			List<AlertGroup> agList = alertGroupService.findByDataType(dataType);
			List<Alert> alertList = alertService.findByDataType(dataType);
			if (cal instanceof InclusionList) {// 非叶子节点
				getTopOfTheASTree(as);// as将是AlertSource的顶
				Set<AlertGroup> ag = new HashSet<AlertGroup>();
				Set<Alert> alt = new HashSet<Alert>();
				getAlertOnAlertSourceTree(ag, alt, as);// 现在as是AlertSource的顶
				if (cal.getAdditionalAlerts() != null && !cal.getAdditionalAlerts().isEmpty()) {
					alt.removeAll(cal.getAdditionalAlerts());
				}
				if (cal.getAlertGroup() != null) {
					ag.remove(cal.getAlertGroup());
				}
				agList.removeAll(ag);
				alertList.removeAll(alt);
			}
			return new Response(ResponseCode.SUCCESS, new AlertListOnAlertSourceEditPage(agList, alertList),
					"List->AlertGroup,List->Alert");
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ResponseCode.UNKNOWN, e);
		}

	}
	/*
	@ApiOperation(value = "Delete the specified AlertSource ", notes = "Response modelType: None", produces = "application/json")
	@RequestMapping(value = "/{airid}", method = RequestMethod.DELETE)
	public Response delete(@PathVariable Long airid) {
		try {
			alertSourceService.deleteByAirId(airid);
			return new Response(ResponseCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ResponseCode.UNKNOWN, e);
		}
	}
	*/
	/*--------------------------------------CriticalAlertingStrategy---------------------------------------------------
	@ApiOperation(value = "Create a Temporary Critical Alerting Strategy", notes = "Create a Temporary Critical Alerting Strategy (Response modelType: CriticalAlertingStrategyDto)", httpMethod = "POST", produces = "application/json")
	@RequestMapping(value = "/critical/savetemp/", method = RequestMethod.POST)
	public Response saveCriticalAlertingStrategyTemporary(@RequestBody CriticalAlertingStrategyDto dto) {
		try {
			AlertSource as = AlertingStrategyAssembler.assembleCriticalAlertingStrategy(dto);
			alertSourceService.save(as);
			return new Response(ResponseCode.SUCCESS, dto, "CriticalAlertingStrategyDto");
		} catch (Exception e) {
			return new Response(ResponseCode.UNKNOWN, e);
		}
	}
	
	@ApiOperation(value = "Create a Critical Alerting Strategy", notes = "Create a Critical Alerting Strategy (Response modelType: CriticalAlertingStrategyDto)", httpMethod = "POST", produces = "application/json")
	@RequestMapping(value = "/critical/save/", method = RequestMethod.POST)
	public Response saveCriticalAlertingStrategy(@RequestBody CriticalAlertingStrategyDto dto) {
		try {
			AlertSource as = AlertingStrategyAssembler.assembleCriticalAlertingStrategy(dto);
			alertSourceService.save(as);
			return new Response(ResponseCode.SUCCESS, dto, "CriticalAlertingStrategyDto");
		} catch (Exception e) {
			return new Response(ResponseCode.UNKNOWN, e);
		}
	}
	
	@ApiOperation(value = "Update the specified Critical Alerting Strategy", notes = "Update the specified Critical Alerting Strategy (Response modelType: CriticalAlertingStrategyDto)", httpMethod = "POST", produces = "application/json")
	@RequestMapping(value = "/critical/update/", method = RequestMethod.POST)
	public Response updateCriticalAlertingStrategy(@RequestBody CriticalAlertingStrategyDto dto) {
		try {
			AlertSource as = AlertingStrategyAssembler.assembleCriticalAlertingStrategy(dto);
			if (as.getId() == null || as.getId() == 0) {
				return new Response(ResponseCode.SYS_0003);
			}
			alertSourceService.update(as);
			return new Response(ResponseCode.SUCCESS, dto, "CriticalAlertingStrategyDto");
		} catch (Exception e) {
			return new Response(ResponseCode.UNKNOWN, e);
		}
	}
	
	@ApiOperation(value = "Get the specified Critical Alerting Strategy", notes = "Get the specified Critical Alerting Strategy (Response modelType: CriticalAlertingStrategyDto)", httpMethod = "GET", produces = "application/json")
	@RequestMapping(value = "/critical/{id}", method = RequestMethod.GET)
	public Response findCriticalAlertingStrategy(@PathVariable Long id) {
		AlertSource as = alertSourceService.findOne(id);
		if (as != null) {
			CriticalAlertingStrategyDto dto = AlertingStrategyAssembler.extractCriticalAlertingStrategy(as);
			return new Response(ResponseCode.SUCCESS, dto, "CriticalAlertingStrategyDto");
		} else {
			return new Response(ResponseCode.UNFOUND);
		}
	}
	*/
	/*--------------------------------------StatisticalAlertingStrategy---------------------------------------------------
	@ApiOperation(value = "Create a Temporary Statistical Alerting Strategy ", notes = "Create a Temporary Statistical Alerting Strategy (Response modelType: StatisticalAlertingStrategyDto)", httpMethod = "POST", produces = "application/json")
	@RequestMapping(value = "/statistical/savetemp/", method = RequestMethod.POST)
	public Response saveStatisticalAlertingStrategyTemporary(@RequestBody StatisticalAlertingStrategyDto dto) {
		try {
			AlertSource sas = AlertingStrategyAssembler.assembleStatisticalAlertingStrategy(dto);
			alertSourceService.save(sas);
			return new Response(ResponseCode.SUCCESS, dto, "StatisticalAlertingStrategyDto");
		} catch (Exception e) {
			return new Response(ResponseCode.UNKNOWN, e);
		}
	}
	
	@ApiOperation(value = "Create a Statistical Alerting Strategy", notes = "Create a Statistical Alerting Strategy (Response modelType: StatisticalAlertingStrategyDto)", httpMethod = "POST", produces = "application/json")
	@RequestMapping(value = "/statistical/save/", method = RequestMethod.POST)
	public Response saveStatisticalAlertingStrategy(@RequestBody StatisticalAlertingStrategyDto dto) {
		try {
			AlertSource sas = AlertingStrategyAssembler.assembleStatisticalAlertingStrategy(dto);
			alertSourceService.save(sas);
			return new Response(ResponseCode.SUCCESS, dto, "StatisticalAlertingStrategyDto");
		} catch (Exception e) {
			return new Response(ResponseCode.UNKNOWN, e);
		}
	}
	
	@ApiOperation(value = "Update the specified Statistical Alerting Strategy", notes = "Update the specified Statistical Alerting Strategy (Response modelType: StatisticalAlertingStrategyDto)", httpMethod = "POST", produces = "application/json")
	@RequestMapping(value = "/statistical/update/", method = RequestMethod.POST)
	public Response updateStatisticalAlertingStrategy(@RequestBody StatisticalAlertingStrategyDto dto) {
		try {
			AlertSource sas = AlertingStrategyAssembler.assembleStatisticalAlertingStrategy(dto);
			if (sas.getId() == null || sas.getId() == 0) {
				return new Response(ResponseCode.SYS_0003);
			}
			alertSourceService.save(sas);
			return new Response(ResponseCode.SUCCESS, dto, "StatisticalAlertingStrategyDto");
		} catch (Exception e) {
			return new Response(ResponseCode.UNKNOWN, e);
		}
	}
	
	@ApiOperation(value = "Get the specified Statistical Alerting Strategy", notes = "Get the specified Statistical Alerting Strategy (Response modelType: StatisticalAlertingStrategyDto)", httpMethod = "GET", produces = "application/json")
	@RequestMapping(value = "/statistical/{id}", method = RequestMethod.GET)
	public Response findStatisticalAlertingStrategy(@PathVariable Long id) {
		AlertSource asa = alertSourceService.findOne(id);
		if (asa != null) {
			StatisticalAlertingStrategyDto sas = AlertingStrategyAssembler.extractStatisticalAlertingStrategy(asa);
			return new Response(ResponseCode.SUCCESS, sas, "StatisticalAlertingStrategyDto");
		} else {
			return new Response(ResponseCode.UNFOUND);
		}
	}
	*/
}
