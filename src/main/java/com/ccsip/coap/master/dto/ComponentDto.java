package com.ccsip.coap.master.dto;

import java.util.List;

public class ComponentDto {
	private Long componentId;
	private String componentName;
	private Long airId;

	private List<ServerDto> servers;

	public ComponentDto() {

	}

	public ComponentDto(Long componentId, String componentName, Long airId, List<ServerDto> servers) {
		super();
		this.componentId = componentId;
		this.airId = airId;
		this.componentName = componentName;
		this.servers = servers;
	}

	public Long getComponentId() {
		return componentId;
	}

	public void setComponentId(Long componentId) {
		this.componentId = componentId;
	}

	public Long getAirId() {
		return airId;
	}

	public void setAirId(Long airId) {
		this.airId = airId;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public List<ServerDto> getServers() {
		return servers;
	}

	public void setServers(List<ServerDto> servers) {
		this.servers = servers;
	}

}
