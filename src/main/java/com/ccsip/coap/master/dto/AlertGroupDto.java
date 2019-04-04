package com.ccsip.coap.master.dto;

import java.util.Set;

public class AlertGroupDto {
	
	 private Long alertGroupId;
	 
	 private String alertGroupName;
	 
	 private String dataTypeName;
	 
	 private long dataTypeId;
	 
	 private Set<AlertDto> alerts;


	public Long getAlertGroupId() {
		return alertGroupId;
	}

	public void setAlertGroupId(Long alertGroupId) {
		this.alertGroupId = alertGroupId;
	}

	public String getDataTypeName() {
		return dataTypeName;
	}

	public long getDataTypeId() {
		return dataTypeId;
	}


	public void setDataTypeName(String dataTypeName) {
		this.dataTypeName = dataTypeName;
	}

	public void setDataTypeId(long dataTypeId) {
		this.dataTypeId = dataTypeId;
	}


	 public String getAlertGroupName() {
		return alertGroupName;
	}

	public void setAlertGroupName(String alertGroupName) {
		this.alertGroupName = alertGroupName;
	}

	public Set<AlertDto> getAlerts() {
		return alerts;
	}

	public void setAlerts(Set<AlertDto> alerts) {
		this.alerts = alerts;
	}
	 

	
}
