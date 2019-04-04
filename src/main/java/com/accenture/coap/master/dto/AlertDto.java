package com.accenture.coap.master.dto;

public class AlertDto {

	private long alertId;

	private String alertName;

	private String severity;

	public AlertDto() {

	}

	public AlertDto(Long alertId, String alertName, String severity) {
		this.alertId = alertId;
		this.alertName = alertName;
		this.severity = severity;
	}

	public long getAlertId() {
		return alertId;
	}

	public String getAlertName() {
		return alertName;
	}

	public String getSeverity() {
		return severity;
	}

	public void setAlertId(long alertId) {
		this.alertId = alertId;
	}

	public void setAlertName(String alertName) {
		this.alertName = alertName;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

}
