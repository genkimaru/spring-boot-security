package com.accenture.coap.master.dto;

import java.util.List;


public class AlertSourceDto {
	private long alertSourceId;
	
	private String alertSourceName;
	
	private List<AlertingStrategyDto> stratedies;

	public long getAlertSourceId() {
		return alertSourceId;
	}

	public String getAlertSourceName() {
		return alertSourceName;
	}

	public List<AlertingStrategyDto> getStratedies() {
		return stratedies;
	}

	public void setAlertSourceId(long alertSourceId) {
		this.alertSourceId = alertSourceId;
	}

	public void setAlertSourceName(String alertSourceName) {
		this.alertSourceName = alertSourceName;
	}

	public void setStratedies(List<AlertingStrategyDto> stratedies) {
		this.stratedies = stratedies;
	}
	
	
	
}
