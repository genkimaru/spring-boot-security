package com.accenture.coap.master.dto;

public class StatisticalAlertingStrategyDto extends AlertingStrategyDto {
	private Integer redThreshold;

	private Integer amberAboveThreshold;

	public Integer getRedThreshold() {
		return redThreshold;
	}

	public void setRedThreshold(Integer redThreshold) {
		this.redThreshold = redThreshold;
	}

	public Integer getAmberAboveThreshold() {
		return amberAboveThreshold;
	}

	public void setAmberAboveThreshold(Integer amberAboveThreshold) {
		this.amberAboveThreshold = amberAboveThreshold;
	}

}
