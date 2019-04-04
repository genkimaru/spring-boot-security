package com.accenture.coap.master.dto;

public class ServerDto {
	private Long serverId;

	private String serverName;

	private Long airId;

	public ServerDto() {

	}

	public ServerDto(Long serverId, String serverName, Long airId) {
		super();
		this.serverId = serverId;
		this.serverName = serverName;
		this.airId = airId;
	}

	public Long getServerId() {
		return serverId;
	}

	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public Long getAirId() {
		return airId;
	}

	public void setAirId(Long airId) {
		this.airId = airId;
	}

}
