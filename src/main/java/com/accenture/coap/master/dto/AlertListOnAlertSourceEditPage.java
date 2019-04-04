package com.accenture.coap.master.dto;

import java.util.List;

import com.accenture.coap.master.metadata.domain.confdata.AlertGroup;
import com.accenture.coap.master.metadata.domain.metadata.Alert;

public class AlertListOnAlertSourceEditPage {
	private List<AlertGroup> alertGroup;
	private List<Alert> additionalAlert;

	public AlertListOnAlertSourceEditPage(List<AlertGroup> ag, List<Alert> alt) {
		this.alertGroup = ag;
		this.additionalAlert = alt;
	}

	public List<AlertGroup> getAlertGroup() {
		return alertGroup;
	}

	public void setAlertGroup(List<AlertGroup> alertGroup) {
		this.alertGroup = alertGroup;
	}

	public List<Alert> getAdditionalAlert() {
		return additionalAlert;
	}

	public void setAdditionalAlert(List<Alert> additionalAlert) {
		this.additionalAlert = additionalAlert;
	}

}
