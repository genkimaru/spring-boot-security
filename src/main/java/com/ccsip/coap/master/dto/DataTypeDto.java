package com.ccsip.coap.master.dto;

public class DataTypeDto {
	private long dataTypeId;

	private long dataSourceId;

	private String dataTypeName;

	public long getDataTypeId() {
		return dataTypeId;
	}

	public long getDataSourceId() {
		return dataSourceId;
	}

	public String getDataTypeName() {
		return dataTypeName;
	}

	public void setDataTypeId(long dataTypeId) {
		this.dataTypeId = dataTypeId;
	}

	public void setDataSourceId(long dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	public void setDataTypeName(String dataTypeName) {
		this.dataTypeName = dataTypeName;
	}

}
