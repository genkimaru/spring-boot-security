package com.accenture.coap.master.assembler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.accenture.coap.master.dto.AlertDto;
import com.accenture.coap.master.dto.AlertGroupDto;
import com.accenture.coap.master.dto.AlertingStrategyDto;
import com.accenture.coap.master.dto.CriticalAlertingStrategyDto;
import com.accenture.coap.master.dto.StatisticalAlertingStrategyDto;
import com.accenture.coap.master.metadata.domain.confdata.*;
import com.accenture.coap.master.metadata.domain.confdata.Level;
import com.accenture.coap.master.metadata.domain.metadata.Alert;
import com.accenture.coap.master.utils.ConvertUtil;

public class AlertingStrategyAssembler {

	public static List<AlertingStrategyDto> extractAlertingStrategyList(List<AlertSource> list) {
		List<AlertingStrategyDto> dtoList = new ArrayList<AlertingStrategyDto>();
		for (AlertSource as : list) {
			dtoList.add(new AlertingStrategyDto(as.getAirId(), as.getId(), as.getName(), as.getDataType().getId(),
					as.getDataType().getName(), as.getLevel().getId(), as.getLevel().getName(), as.getStatus()));
		}
		return dtoList;
	}

	public static AlertSource assembleCriticalAlertingStrategy(CriticalAlertingStrategyDto dto) {
		AlertSource as = new AlertSource();
		as.setAirId(dto.getAirId());
		as.setId(dto.getStrategyId());
		as.setName(dto.getStrategyName());
		as.setDataType(new DataType(dto.getDataTypeId()));
		as.setLevel(new Level(dto.getInfrastructureUnitId()));
		as.setStatus(dto.getStatus());
		CriticalAlertList cal = null;
		List<AlertDto> alertsDto = dto.getAlerts();
		if (alertsDto != null && !alertsDto.isEmpty()) {
			cal = new InclusionList();
			Set<Alert> alerts = new HashSet<Alert>();
			for (AlertDto alertDto : alertsDto) {
				alerts.add(AlertAssembler.assemble(alertDto));
			}
			cal.setAdditionalAlerts(alerts);
		}
		AlertGroupDto alertGroupDto = dto.getAlertGroup();
		if (alertGroupDto != null) {
			if (cal == null) {
				cal = new InclusionList();
			}
			cal.setAlertGroup(ConvertUtil.assemble(alertGroupDto));
		}
		if (cal != null) {
			as.setCriticalAlertList(cal);
		}
		return as;
	}

	public static AlertSource assembleStatisticalAlertingStrategy(StatisticalAlertingStrategyDto dto) {
		AlertSource as = new AlertSource();
		as.setAirId(dto.getAirId());
		as.setId(dto.getStrategyId());
		as.setName(dto.getStrategyName());
		as.setDataType(new DataType(dto.getDataTypeId()));
		as.setLevel(new Level(dto.getInfrastructureUnitId()));
		as.setStatisticalThreshold(new StatisticalThreshold(dto.getRedThreshold(), dto.getAmberAboveThreshold()));
		as.setStatus(dto.getStatus());
		return as;
	}

	@SuppressWarnings("unchecked")
	public static CriticalAlertingStrategyDto extractCriticalAlertingStrategy(AlertSource as) {
		CriticalAlertingStrategyDto dto = new CriticalAlertingStrategyDto();
		dto.setAirId(as.getAirId());
		dto.setStrategyId(as.getId());
		dto.setStrategyName(as.getName());
		dto.setDataTypeId(as.getDataType().getId());
		dto.setDataTypeName(as.getDataType().getName());
		dto.setInfrastructureUnitId(as.getLevel().getId());
		dto.setInfrastructureUnitName(as.getLevel().getName());
		CriticalAlertList cal = as.getCriticalAlertList();
		if (cal != null) {
			dto.setAlertGroup(ConvertUtil.extract(cal.getAlertGroup()));
			dto.setAlerts(AlertAssembler.extractList((List<Alert>) cal.getAdditionalAlerts()));
		}
		dto.setStatus(as.getStatus());
		return dto;
	}

	public static StatisticalAlertingStrategyDto extractStatisticalAlertingStrategy(AlertSource as) {
		StatisticalAlertingStrategyDto dto = new StatisticalAlertingStrategyDto();
		dto.setAirId(as.getAirId());
		dto.setStrategyId(as.getId());
		dto.setStrategyName(as.getName());
		dto.setDataTypeId(as.getDataType().getId());
		dto.setDataTypeName(as.getDataType().getName());
		dto.setInfrastructureUnitId(as.getLevel().getId());
		dto.setInfrastructureUnitName(as.getLevel().getName());
		if (as.getStatisticalThreshold() != null) {
			dto.setAmberAboveThreshold(as.getStatisticalThreshold().getAmberAboveThreshold());
			dto.setRedThreshold(as.getStatisticalThreshold().getRedThreshold());
		}
		dto.setStatus(as.getStatus());
		return dto;
	}

}
