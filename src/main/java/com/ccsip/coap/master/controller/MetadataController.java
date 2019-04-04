package com.ccsip.coap.master.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsip.coap.master.assembler.AlertAssembler;
import com.ccsip.coap.master.assembler.AppAssembler;
import com.ccsip.coap.master.dto.GetAlert;
import com.ccsip.coap.master.dto.GetCriticalAlertList;
import com.ccsip.coap.master.dto.GetRootAlertSource;
import com.ccsip.coap.master.dto.GetServer;
import com.ccsip.coap.master.metadata.domain.confdata.AlertSource;
import com.ccsip.coap.master.metadata.domain.confdata.Level;
import com.ccsip.coap.master.metadata.domain.metadata.Alert;
import com.ccsip.coap.master.metadata.domain.metadata.App;
import com.ccsip.coap.master.metadata.domain.metadata.ScomMetric;
import com.ccsip.coap.master.metadata.domain.metadata.WebSitePulse;
import com.ccsip.coap.master.metadata.service.AlertService;
import com.ccsip.coap.master.metadata.service.AlertSourceService;
import com.ccsip.coap.master.metadata.service.AppService;
import com.ccsip.coap.master.metadata.service.CriticalAlertListService;
import com.ccsip.coap.master.metadata.service.LevelService;
import com.ccsip.coap.master.metadata.service.ScomMetricService;
import com.ccsip.coap.master.metadata.service.WebSitePulseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Consuming a REST API seems simple enough, but before you can do that you must obtain authorization to access the user's resources.")
@ApiResponses(value = { @ApiResponse(code = 401, message = "NO LOGIN, NO AUTH", response = Void.class),
		@ApiResponse(code = 403, message = "NO LOGIN, NO AUTH", response = Void.class) })
@RestController
@RequestMapping("confdata")
public class MetadataController {
	@Autowired
	private AlertSourceService alertSourceService;
	@Autowired
	private AppService appService;
	@Autowired
	private CriticalAlertListService criticalAlertListService;
	@Autowired
	private ScomMetricService scomMetricService;
	@Autowired
	private AlertService alertService;
	@Autowired
	private WebSitePulseService webSitePulseService;
	@Autowired
	private LevelService levelService;

	@RequestMapping(value = "/getRootAlertSource", method = RequestMethod.GET)
	public List<GetRootAlertSource> getRootAlertSource(HttpServletRequest request) {
		Long airId = Long.valueOf(request.getParameter("airId") == null || "".equals(request.getParameter("airId"))
				? "0" : request.getParameter("airId"));
		Long dataTypeId = Long
				.valueOf(request.getParameter("dataTypeId") == null || "".equals(request.getParameter("dataTypeId"))
						? "0" : request.getParameter("dataTypeId"));
		List<AlertSource> asList = null;
		if (airId != null && airId > 0 && dataTypeId != null && dataTypeId > 0) {
			asList = alertSourceService.findByAirIdAndDataTypeIdAndParentIsNull(airId, dataTypeId);
		} else if ((airId != null && airId > 0)) {
			asList = alertSourceService.findByAirIdAndParentIsNull(airId);
		} else {
			asList = alertSourceService.findAllParentIsNull();
		}

		List<GetRootAlertSource> res = new ArrayList<GetRootAlertSource>();
		for (AlertSource as : asList) {
			res.add(new GetRootAlertSource(as.getAirId(), as.getDataType(), as));
		}
		return res;
	}

	@RequestMapping(value = "/getCriticalAlertList", method = RequestMethod.GET)
	public List<GetCriticalAlertList> getCriticalAlertList(HttpServletRequest request) {
		String dType = request.getParameter("dtype");
		List<GetCriticalAlertList> calList = criticalAlertListService.findByDType(dType);
		return calList;
	}

	@RequestMapping(value = "/getApp", method = RequestMethod.GET)
	public List<App> getApp(HttpServletRequest request) {
		Long airId = Long.valueOf(request.getParameter("airId") == null || "".equals(request.getParameter("airId"))
				? "0" : request.getParameter("airId"));
		List<App> app = null;
		if (airId != null && airId > 0) {
			app = new ArrayList<App>();
			App a = appService.findByAirId(airId);
			if (a != null)
				app.add(a);
		} else {
			app = appService.findAll();
		}
		AppAssembler.excludeLoopLinding(app);
		return app;
	}

	@RequestMapping(value = "/getServer", method = RequestMethod.GET)
	public List<GetServer> getServer(HttpServletRequest request) {

		List<GetServer> app = appService.getServer();

		return app;
	}

	@RequestMapping(value = "/getAlertList", method = RequestMethod.GET)
	public List<Alert> getAlertList(HttpServletRequest request) {

		List<Alert> list = alertService.findAll();

		return list;
	}

	@RequestMapping(value = "/getAlertListFromCriticalAlertList", method = RequestMethod.GET)
	public List<GetAlert> getAlerts(HttpServletRequest request) {
		Long criticalAlertListId = Long.valueOf(request.getParameter("criticalAlertListId") == null
				|| "".equals(request.getParameter("criticalAlertListId")) ? "0"
						: request.getParameter("criticalAlertListId"));
		List<GetAlert> alertList = null;
		if (criticalAlertListId != null && criticalAlertListId > 0) {
			alertList = new ArrayList<GetAlert>();
			Set<Alert> alerts = criticalAlertListService.findAlertsById(criticalAlertListId);
			if (alerts != null) {
				alertList.add(new GetAlert(criticalAlertListId, alerts));
			}
		} else {
			alertList = criticalAlertListService.findAlerts();
		}
		AlertAssembler.excludeLoopLinding(alertList);
		return alertList;
	}

	@RequestMapping(value = "/getScomMetric", method = RequestMethod.GET)
	public List<ScomMetric> getScomMetric(HttpServletRequest request) {
		return scomMetricService.findAllKeyIsNull();
	}

	@RequestMapping(value = "/getWebSitePulse", method = RequestMethod.GET)
	public List<WebSitePulse> getWebSitePulse(HttpServletRequest request) {
		return webSitePulseService.findAll();
	}
	
	@RequestMapping(value = "/getLevels", method = RequestMethod.GET)
	public List<Level> getLevels(HttpServletRequest request) {
		return levelService.findAll();
	}
}
