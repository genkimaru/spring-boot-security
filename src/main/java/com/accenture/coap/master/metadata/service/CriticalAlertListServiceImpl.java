package com.accenture.coap.master.metadata.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.accenture.coap.master.dto.GetAlert;
import com.accenture.coap.master.dto.GetCriticalAlertList;
import com.accenture.coap.master.metadata.domain.confdata.AlertGroup;
import com.accenture.coap.master.metadata.domain.confdata.CriticalAlertList;
import com.accenture.coap.master.metadata.domain.metadata.Alert;
import com.accenture.coap.master.metadata.repository.AlertSourceRepository;
import com.accenture.coap.master.metadata.repository.CriticalAlertListRepository;

@org.springframework.stereotype.Component("criticalAlertListService")
@Transactional
public class CriticalAlertListServiceImpl implements CriticalAlertListService {
	private final CriticalAlertListRepository criticalAlertListRepository;
	private final AlertSourceRepository alertSourceRepository;

	public CriticalAlertListServiceImpl(CriticalAlertListRepository criticalAlertListRepository,
			AlertSourceRepository alertSourceRepository) {
		super();
		this.criticalAlertListRepository = criticalAlertListRepository;
		this.alertSourceRepository = alertSourceRepository;
	}

	@Override
	public CriticalAlertList save(CriticalAlertList criticalAlertList) {
		criticalAlertList = criticalAlertListRepository.save(criticalAlertList);
		return criticalAlertList;
	}

	@Override
	public CriticalAlertList update(CriticalAlertList criticalAlertList) {
		criticalAlertList = criticalAlertListRepository.save(criticalAlertList);
		return criticalAlertList;
	}

	@Override
	public CriticalAlertList findById(Long id) {
		CriticalAlertList criticalAlertList = criticalAlertListRepository.findOne(id);
		return criticalAlertList;
	}

	@Override
	public Set<Alert> findAlertsById(Long id) {
		Set<Alert> alertSet = new HashSet<Alert>();
		CriticalAlertList criticalAlertList = findById(id);
		AlertGroup ag = criticalAlertList.getAlertGroup();
		if (ag != null) {
			alertSet.addAll(ag.getAlerts());
		}
		Set<Alert> additionalAlerts = criticalAlertList.getAdditionalAlerts();
		if (additionalAlerts != null) {
			alertSet.addAll(additionalAlerts);
		}
		return alertSet;
	}

	@Override
	public List<CriticalAlertList> findAll() {
		return (List<CriticalAlertList>) criticalAlertListRepository.findAll();
	}

	@Override
	public List<GetAlert> findAlerts() {
		List<GetAlert> res = new ArrayList<GetAlert>();
		List<CriticalAlertList> calList = findAll();
		if (calList != null) {
			for (CriticalAlertList cal : calList) {
				Set<Alert> alertSet = new HashSet<Alert>();
				AlertGroup ag = cal.getAlertGroup();
				if (ag != null) {
					alertSet.addAll(ag.getAlerts());
				}
				Set<Alert> additionalAlerts = cal.getAdditionalAlerts();
				if (additionalAlerts != null) {
					alertSet.addAll(additionalAlerts);
				}
				res.add(new GetAlert(cal.getId(), alertSet));
			}
		}

		return res;
	}

	@Override
	public List<GetCriticalAlertList> findByDType(String dType) {
		List<GetCriticalAlertList> res = new ArrayList<GetCriticalAlertList>();
		List<Object[]> calList = criticalAlertListRepository.findByDType(dType);
		for (Object[] e : calList) {
			BigInteger criticalAlertId = (BigInteger) e[0];
			BigInteger alertSourceId = (BigInteger) e[1];
			res.add(new GetCriticalAlertList(alertSourceId.longValue(), criticalAlertId.longValue()));
		}
		return res;
	}

}
