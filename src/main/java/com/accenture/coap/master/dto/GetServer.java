package com.accenture.coap.master.dto;

import java.util.HashMap;
import java.util.Map;

public class GetServer {
	private Long airId;
	private String name;
	private String function;
	private final static Map<String, String> functionMap = new HashMap<String, String>();
	static {
		// hard-coding for FUNCTION mapping
		functionMap.put("DB Group", "Database");
		functionMap.put("Web Group", "Web");
		functionMap.put("Application Group", "Application");
	}

	public GetServer(Long airId, String hostname, String function) {
		super();
		this.airId = airId;
		this.name = hostname;
		this.function = functionMap.get(function);
	}

	public Long getAirId() {
		return airId;
	}

	public void setAirId(Long airId) {
		this.airId = airId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((airId == null) ? 0 : airId.hashCode());
		result = prime * result + ((function == null) ? 0 : function.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GetServer other = (GetServer) obj;
		if (airId == null) {
			if (other.airId != null)
				return false;
		} else if (!airId.equals(other.airId))
			return false;
		if (function == null) {
			if (other.function != null)
				return false;
		} else if (!function.equals(other.function))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
