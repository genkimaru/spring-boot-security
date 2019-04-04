package com.accenture.coap.master.dto;

import java.util.Set;

import com.accenture.coap.master.metadata.domain.metadata.Alert;

public class GetAlert {
	private Long criticalListId;
	private Set<Alert> alertList;

	public GetAlert() {

	}

	public GetAlert(Long id, Set<Alert> alertSet) {
		this.criticalListId = id;
		this.alertList = alertSet;
	}

	public Long getCriticalListId() {
		return criticalListId;
	}

	public void setCriticalListId(Long criticalListId) {
		this.criticalListId = criticalListId;
	}

	public Set<Alert> getAlertList() {
		return alertList;
	}

	public void setAlertList(Set<Alert> alertList) {
		this.alertList = alertList;
	}

}
