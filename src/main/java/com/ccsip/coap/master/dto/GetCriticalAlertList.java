package com.ccsip.coap.master.dto;

import com.ccsip.coap.master.metadata.domain.confdata.CriticalAlertList;
import com.ccsip.coap.master.metadata.domain.confdata.InclusionList;

public class GetCriticalAlertList {
	private Long alertSourceId;
	private CriticalAlertList criticalAlertList;

	public GetCriticalAlertList(Long alertSourceId, Long crititalAlertListId) {
		this.alertSourceId = alertSourceId;
		this.criticalAlertList = new InclusionList();
		this.criticalAlertList.setId(crititalAlertListId);
	}

	public Long getAlertSourceId() {
		return alertSourceId;
	}

	public void setAlertSourceId(Long alertSourceId) {
		this.alertSourceId = alertSourceId;
	}

	public CriticalAlertList getCriticalAlertList() {
		return criticalAlertList;
	}

	public void setCriticalAlertList(CriticalAlertList criticalAlertList) {
		this.criticalAlertList = criticalAlertList;
	}

}
