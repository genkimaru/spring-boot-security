package com.accenture.coap.master.dto;

import java.util.List;


public class AppDto {
	private Long airId;
	private String appName;
	private String serviceTier;
	private List<ComponentDto> components;

	public String getServiceTier() {
		return serviceTier;
	}

	public void setServiceTier(String serviceTier) {
		this.serviceTier = serviceTier;
	}

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

	public List<ComponentDto> getComponents() {
		return components;
	}

	public void setComponents(List<ComponentDto> components) {
		this.components = components;
	}

}
