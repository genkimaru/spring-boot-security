package com.ccsip.coap.master.dto;

public class AppListDto {
	private Long airId;
	private String appName = "";

	public Long getAirId() {
		return airId;
	}

	public void setAirId(Long airId) {
		this.airId = airId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

}
