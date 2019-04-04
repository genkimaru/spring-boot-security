package com.ccsip.coap.master.dto;

public class AlertingStrategyDto {
	private Long airId;
	private Long strategyId;
	private String strategyName;
	private Long dataTypeId;
	private String dataTypeName;
	private Long infrastructureUnitId;
	private String infrastructureUnitName;
	private String status;

	public AlertingStrategyDto() {
		super();
	}

	public AlertingStrategyDto(Long airId, Long strategyId, String strategyName, Long dataTypeId, String dataTypeName,
			Long infrastructureUnitId, String infrastructureUnitName, String status) {
		super();
		this.airId = airId;
		this.strategyId = strategyId;
		this.strategyName = strategyName;
		this.dataTypeId = dataTypeId;
		this.dataTypeName = dataTypeName;
		this.infrastructureUnitId = infrastructureUnitId;
		this.infrastructureUnitName = infrastructureUnitName;
		this.status = status;
	}

	public String getDataTypeName() {
		return dataTypeName;
	}

	public void setDataTypeName(String dataTypeName) {
		this.dataTypeName = dataTypeName;
	}

	public String getInfrastructureUnitName() {
		return infrastructureUnitName;
	}

	public void setInfrastructureUnitName(String infrastructureUnitName) {
		this.infrastructureUnitName = infrastructureUnitName;
	}

	public Long getAirId() {
		return airId;
	}

	public void setAirId(Long airId) {
		this.airId = airId;
	}

	public Long getStrategyId() {
		return strategyId;
	}

	public void setStrategyId(Long strategyId) {
		this.strategyId = strategyId;
	}

	public String getStrategyName() {
		return strategyName;
	}

	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}

	public Long getDataTypeId() {
		return dataTypeId;
	}

	public void setDataTypeId(Long dataTypeId) {
		this.dataTypeId = dataTypeId;
	}

	public Long getInfrastructureUnitId() {
		return infrastructureUnitId;
	}

	public void setInfrastructureUnitId(Long infrastructureUnitId) {
		this.infrastructureUnitId = infrastructureUnitId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
