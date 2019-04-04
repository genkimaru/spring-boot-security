package com.ccsip.coap.master.metadata.service;

import java.util.List;
import java.util.Set;

import com.ccsip.coap.master.dto.GetAlert;
import com.ccsip.coap.master.dto.GetCriticalAlertList;
import com.ccsip.coap.master.metadata.domain.confdata.CriticalAlertList;
import com.ccsip.coap.master.metadata.domain.metadata.Alert;

public interface CriticalAlertListService {
	CriticalAlertList save(CriticalAlertList criticalAlertList);

	CriticalAlertList update(CriticalAlertList criticalAlertList);

	List<CriticalAlertList> findAll();

	CriticalAlertList findById(Long id);

	Set<Alert> findAlertsById(Long id);

	List<GetAlert> findAlerts();

	List<GetCriticalAlertList> findByDType(String dType);
}
