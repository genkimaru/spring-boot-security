package com.accenture.coap.master.dto;

import java.util.List;

public class CriticalAlertingStrategyDto extends AlertingStrategyDto {
	private AlertGroupDto alertGroup;
	private List<AlertDto> alerts;

	public List<AlertDto> getAlerts() {
		return alerts;
	}

	public void setAlerts(List<AlertDto> alerts) {
		this.alerts = alerts;
	}

	public AlertGroupDto getAlertGroup() {
		return alertGroup;
	}

	public void setAlertGroup(AlertGroupDto alertGroup) {
		this.alertGroup = alertGroup;
	}

}
