package com.accenture.coap.master.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.accenture.coap.master.dto.AlertDto;
import com.accenture.coap.master.dto.GetAlert;
import com.accenture.coap.master.metadata.domain.metadata.Alert;

public class AlertAssembler {

	public static List<Alert> assembleList(List<AlertDto> dtoList) {
		List<Alert> list = new ArrayList<Alert>();
		for (AlertDto dto : dtoList) {
			list.add(assemble(dto));
		}
		return list;
	}

	public static Alert assemble(AlertDto dto) {
		Alert alert = new Alert();
		alert.setId(dto.getAlertId());
		alert.setName(dto.getAlertName());
		alert.setSeverity(dto.getSeverity());
		return alert;
	}

	public static AlertDto extract(Alert at) {
		AlertDto dto = new AlertDto();
		dto.setAlertId(at.getId());
		dto.setAlertName(at.getName());
		dto.setSeverity(at.getSeverity());
		return dto;
	}

	public static List<AlertDto> extractList(List<Alert> alertList) {
		List<AlertDto> list = new ArrayList<AlertDto>();
		for (Alert at : alertList) {
			list.add(extract(at));
		}
		return list;
	}

	public static void excludeLoopLinding(List<GetAlert> alertList) {
		if (alertList != null)
			for (GetAlert entry : alertList) {
				excludeLoopLinding(entry.getAlertList());
			}
	}

	public static void excludeLoopLinding(Set<Alert> alertList) {
		if (alertList != null)
			for (Alert alert : alertList) {
				alert.setDataType(null);
			}
	}
}
