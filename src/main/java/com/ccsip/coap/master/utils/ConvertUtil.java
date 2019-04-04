package com.ccsip.coap.master.utils;

import java.util.HashSet;
import java.util.Set;

import com.ccsip.coap.master.dto.AlertDto;
import com.ccsip.coap.master.dto.AlertGroupDto;
import com.ccsip.coap.master.dto.DataTypeDto;
import com.ccsip.coap.master.metadata.domain.confdata.AlertGroup;
import com.ccsip.coap.master.metadata.domain.confdata.DataType;
import com.ccsip.coap.master.metadata.domain.metadata.Alert;

public class ConvertUtil {
	/**
	 * alertDto 从controller层到serveice层的转化
	 * 
	 * @param alertDto
	 * @return alertGroup
	 */
	public static AlertGroup assemble(AlertGroupDto alertGroupDto) {
		AlertGroup alertGroup = new AlertGroup();
		if (alertGroupDto.getAlertGroupId() != null && alertGroupDto.getAlertGroupId() > 0) {
			alertGroup.setId(alertGroupDto.getAlertGroupId());
		}
		alertGroup.setName(alertGroupDto.getAlertGroupName());
		alertGroup.setDataType(new DataType(alertGroupDto.getDataTypeId()));
		Set<AlertDto> alertDtos = alertGroupDto.getAlerts();
		if (alertDtos != null && alertDtos.size() > 0) {
			HashSet<Alert> alerts = new HashSet<Alert>();
			for (AlertDto dto : alertDtos) {
				alerts.add(new Alert(dto.getAlertId()));
			}
			alertGroup.setAlerts(alerts);
		}

		return alertGroup;
	}

	/**
	 * alertDto 从serveice层到controller层的转化
	 * 
	 * @param alertGroup
	 * @return
	 */
	public static AlertGroupDto extract(AlertGroup alertGroup) {
		AlertGroupDto alertGroupDto = new AlertGroupDto();
		alertGroupDto.setAlertGroupId(alertGroup.getId());
		alertGroupDto.setAlertGroupName(alertGroup.getName());
		alertGroupDto.setDataTypeId(alertGroup.getDataType().getId());
		alertGroupDto.setDataTypeName(alertGroup.getDataType().getName());
		Set<Alert> alerts = alertGroup.getAlerts();

		if (alerts != null) {
			Set<AlertDto> dtos = new HashSet<AlertDto>();
			for (Alert alert : alerts) {
				dtos.add(new AlertDto(alert.getId(), alert.getName(), alert.getSeverity()));
			}
			alertGroupDto.setAlerts(dtos);
		}

		return alertGroupDto;
	}

	public static DataTypeDto extract(DataType dataType) {
		DataTypeDto dataTypeDto = new DataTypeDto();
		dataTypeDto.setDataTypeId(dataType.getId());
		dataTypeDto.setDataSourceId(dataType.getDataSource().getId());
		dataTypeDto.setDataTypeName(dataType.getName());
		return dataTypeDto;

	}

	public static AlertDto extract(Alert alert) {
		AlertDto alertDto = new AlertDto();
		alertDto.setAlertId(alert.getId());
		alertDto.setAlertName(alert.getName());
		alertDto.setSeverity(alert.getSeverity());
		return alertDto;

	}

}
