package com.ccsip.coap.master.dto;

import java.io.Serializable;

import com.ccsip.coap.master.metadata.domain.confdata.AlertSource;
import com.ccsip.coap.master.metadata.domain.confdata.DataType;

public class AlertSourceExt implements Serializable {

	private static final long serialVersionUID = 96185106070401734L;

	private DataType dataType;

	private AlertSource alertSource;

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AlertSource getAlertSource() {
		return alertSource;
	}

	public void setAlertSource(AlertSource alertSource) {
		this.alertSource = alertSource;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

}
