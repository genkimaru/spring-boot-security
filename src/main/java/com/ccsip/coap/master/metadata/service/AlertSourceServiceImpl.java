/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ccsip.coap.master.metadata.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ccsip.coap.master.dto.AlertSourceFormDto;
import com.ccsip.coap.master.metadata.domain.confdata.AlertGroup;
import com.ccsip.coap.master.metadata.domain.confdata.AlertSource;
import com.ccsip.coap.master.metadata.domain.confdata.AlertSourceTemplate;
import com.ccsip.coap.master.metadata.domain.confdata.CriticalAlertList;
import com.ccsip.coap.master.metadata.domain.confdata.DataType;
import com.ccsip.coap.master.metadata.domain.confdata.ExclusionList;
import com.ccsip.coap.master.metadata.domain.confdata.InclusionList;
import com.ccsip.coap.master.metadata.domain.confdata.StatisticalThreshold;
import com.ccsip.coap.master.metadata.domain.metadata.Alert;
import com.ccsip.coap.master.metadata.domain.metadata.App;

import com.ccsip.coap.master.metadata.repository.AlertSourceRepository;
import com.ccsip.coap.master.metadata.repository.AlertSourceTemplateRepository;
import com.ccsip.coap.master.metadata.repository.DataTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("alertSourceService")
@Transactional
class AlertSourceServiceImpl implements AlertSourceService {
	private final static Map<Long, Long> airs = Collections.synchronizedMap(new HashMap<Long, Long>());
	private final AlertSourceTemplateRepository alertSourceTemplateRepository;
	private final AlertSourceRepository alertSourceRepository;
	private final DataTypeRepository dataTypeRepository;
	@Autowired
	private AppService appService;

	public AlertSourceServiceImpl(AlertSourceTemplateRepository alertSourceTemplateRepository,
			AlertSourceRepository AlertSourceRepository, DataTypeRepository dataTypeRepository) {
		this.alertSourceTemplateRepository = alertSourceTemplateRepository;
		this.alertSourceRepository = AlertSourceRepository;
		this.dataTypeRepository = dataTypeRepository;
	}

	@Override
	public AlertSource save(AlertSource alertSource) {
		return alertSourceRepository.save(alertSource);
	}

	@Override
	public int update(AlertSourceFormDto dto) {
		try {
			AlertSource original = findOne(dto.getId());

			original.setStatisticalThreshold(
					new StatisticalThreshold(dto.getRedThreshold(), dto.getAmberAboveThreshold()));

			CriticalAlertList criticalAlertList = original.getCriticalAlertList();
			criticalAlertList.setAlertGroup(new AlertGroup(dto.getAlertGroupId()));
			Set<Long> alertIds = dto.getAdditionalAlertId();
			Set<Alert> additionalAlert = new HashSet<Alert>();
			for (Long id : alertIds) {
				additionalAlert.add(new Alert(id));
			}
			criticalAlertList.setAdditionalAlerts(additionalAlert);
			alertSourceRepository.save(original);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public int update(AlertSource as) {
		try {
			AlertSource original = findOne(as.getId());
			if (as.getStatisticalThreshold() != null) {
				original.setStatisticalThreshold(as.getStatisticalThreshold());
			}
			if (as.getCriticalAlertList() != null) {
				original.setCriticalAlertList(as.getCriticalAlertList());
			}
			original.setStatus(as.getStatus());
			alertSourceRepository.save(original);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public void delete(Long asId) {
		alertSourceRepository.delete(asId);
	}

	@Override
	public void deleteByAirId(Long airid) {
		alertSourceRepository.deleteByAirId(airid);

	}

	@Override
	public List<AlertSource> findBy() {
		return (List<AlertSource>) alertSourceRepository.findAll();
	}

	@Override
	public List<AlertSource> findByAirId(long airId) {
		return alertSourceRepository.findByAirId(airId);
	}

	@Override
	public List<AlertSource> findByAirId2(long airId) {
		return alertSourceRepository.findByAirId2(airId);
	}

	@Override
	public AlertSource findByName(String name) {
		return alertSourceRepository.findByNameAllIgnoringCase(name);
	}

	@Override
	public AlertSource findOne(long id) {
		return alertSourceRepository.findOne(id);
	}

	/**
	 * 根据AlertSource模板生成相应的AlertSource
	 * 
	 * @param app
	 * @param template
	 * @param as
	 * @return
	 */
	public AlertSource assemble(App app, AlertSourceTemplate template, AlertSource as) {
		if (as == null)
			as = new AlertSource();
		as.setAirId(app.getAirId());
		as.setDataType(template.getDataType());
		as.setLevel(template.getLevel());
		as.setRefId(app.getId());
		as.setName(app.getName() + "-" + template.getName() + "-AlertSource");
		if (template.getStatisticalThreshold() != null) {
			as.setStatisticalThreshold(template.getStatisticalThreshold());
		}

		CriticalAlertList criticalAlertList = template.getCriticalAlertList();
		if (criticalAlertList != null) {
			CriticalAlertList cal = new ExclusionList();
			if (criticalAlertList instanceof InclusionList) {
				cal = new InclusionList();
			}
			if (criticalAlertList.getAlertGroup() != null) {
				cal.setAlertGroup(criticalAlertList.getAlertGroup());
			}
			if (criticalAlertList.getAdditionalAlerts() != null) {
				cal.setAdditionalAlerts(new HashSet<Alert>() {
					private static final long serialVersionUID = 1L;
					{
						addAll(criticalAlertList.getAdditionalAlerts());
					}
				});
			}
			as.setCriticalAlertList(cal);
		}
		Set<AlertSourceTemplate> childrenTemplate = template.getChildren();
		if (childrenTemplate != null) {
			for (AlertSourceTemplate childTemplate : childrenTemplate) {
				Set<AlertSource> children = as.getChildren();
				if (children == null) {
					children = new HashSet<AlertSource>();
					as.setChildren(children);
				}
				AlertSource child = new AlertSource();
				assemble(app, childTemplate, child);// child
				child.setParent(as);
				children.add(child);
			}
		}
		return as;
	}

	@Override
	public List<AlertSource> initAlertSource(Long airid) {
		if (airs.get(airid) == null) {
			airs.put(airid, airid);
		}
		synchronized (airs.get(airid)) {
			List<AlertSource> res = findByAirId2(airid);
			if (res != null && !res.isEmpty()) {
				return res;
			} else {
				res = new ArrayList<AlertSource>();
			}

			List<DataType> dtList = (List<DataType>) dataTypeRepository.findByIsAlert();
			App app = appService.findByAirId(airid);
			if (dtList != null && app != null) {
				for (DataType dataType : dtList) {
					AlertSourceTemplate topLevel = alertSourceTemplateRepository.findByDataTypeID(dataType.getId());
					AlertSource as = new AlertSource();
					assemble(app, topLevel, as);
					save(as);
					res.add(as);
				}
			}
			return res;
		}
	}

	@Override
	public List<AlertSource> findByAirIdAndDataTypeIdAndParentIsNull(Long airId, Long dataTypeId) {
		return alertSourceRepository.findByAirIdAndDataTypeIdAndParentIsNull(airId, dataTypeId);
	}

	@Override
	public List<AlertSource> findByAirIdAndParentIsNull(long airId) {
		return alertSourceRepository.findByAirIdAndParentIsNull(airId);
	}

	@Override
	public List<AlertSource> findAllParentIsNull() {
		return (List<AlertSource>) alertSourceRepository.findAllAndParentIsNull();
	}

}
