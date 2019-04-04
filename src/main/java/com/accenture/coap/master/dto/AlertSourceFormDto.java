package com.accenture.coap.master.dto;

import java.util.Set;

public class AlertSourceFormDto {
	private Long id;

	private Integer redThreshold;

	private Integer amberAboveThreshold;

	private String listType;

	private Long alertGroupId;

	private Set<Long> additionalAlertId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Long getAlertGroupId() {
		return alertGroupId;
	}

	public void setAlertGroupId(Long alertGroupId) {
		this.alertGroupId = alertGroupId;
	}

	public Set<Long> getAdditionalAlertId() {
		return additionalAlertId;
	}

	public void setAdditionalAlertId(Set<Long> additionalAlertId) {
		this.additionalAlertId = additionalAlertId;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

}
