/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by alertGrouplicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.accenture.coap.master.metadata.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.coap.master.metadata.domain.confdata.AlertGroup;
import com.accenture.coap.master.metadata.domain.confdata.CriticalAlertList;
import com.accenture.coap.master.metadata.domain.confdata.DataType;
import com.accenture.coap.master.metadata.repository.AlertGroupRepository;
import com.accenture.coap.master.metadata.repository.CriticalAlertListRepository;

@Component("alertGroupService")	
@Transactional
public class AlertGroupServiceImpl implements AlertGroupService {

	private final AlertGroupRepository alertGroupRepository;
	
	private final CriticalAlertListRepository criticalAlertListRepository;

	public AlertGroupServiceImpl(AlertGroupRepository alertGroupRepository , CriticalAlertListRepository criticalAlertListRepository) {
		this.alertGroupRepository = alertGroupRepository;
		this.criticalAlertListRepository = criticalAlertListRepository;
	}

	@Override
	public List<AlertGroup> findAll() {
		 Iterable<AlertGroup> alertGroups = alertGroupRepository.findAll();
		 List<AlertGroup> list = new ArrayList<AlertGroup>();
		 alertGroups.forEach(list::add);
		 return list;
	}

	@Override
	public AlertGroup save(AlertGroup alertGroup) {
		List<AlertGroup> alertGroups = findAll();
		for(AlertGroup ag : alertGroups){
			if(alertGroup.getName().equalsIgnoreCase(ag.getName())){
				return null;
			}
		}
		return  alertGroupRepository.save(alertGroup);
	}

	@Override
	public Iterable<AlertGroup> save(Iterable<AlertGroup> alertGroups) {
		return alertGroupRepository.save(alertGroups);
	}

	@Override
	public AlertGroup findOne(long id) {
		return alertGroupRepository.findOne(id);
	}

	@Override
	public AlertGroup update(AlertGroup alertGroup) {
		List<AlertGroup> alertGroups = findAll();
		AlertGroup self = findOne(alertGroup.getId());
		for(AlertGroup ag : alertGroups){
			if(alertGroup.getName().equalsIgnoreCase(ag.getName()) && !alertGroup.getName().equalsIgnoreCase(self.getName())){
				return null;
			}
		}
		return alertGroupRepository.save(alertGroup);
		 
	}

	@Override
	public void delete(AlertGroup alertGroup) {

		alertGroupRepository.delete(alertGroup);
		
	}

	@Override
	public List<AlertGroup> findByDataTypeIdAndName(AlertGroup alertGroup) {
		// dataTypeId is "", groupname is ""
		if(alertGroup.getDataType().getId() == 0 && "".equals(alertGroup.getName().trim())){
			return  findAll();
		}else if(alertGroup.getDataType().getId() == 0){
			return alertGroupRepository.findByName(alertGroup.getName());
		}else if("".equals(alertGroup.getName().trim())){
			return alertGroupRepository.findByDataType(alertGroup.getDataType());
		}else{
			return alertGroupRepository.findByDataTypeIdAndName(alertGroup.getDataType().getId(), alertGroup.getName());
		}

	}

	
	@Override
	public void delete(long id) {
			alertGroupRepository.delete(id);
	}

	
	/**
	 * 
	 * @param id
	 * @return   0 : in use  ; id  : ok
	 */
	@Override
	public long deleteById(long id) {
		// firstly , check if the alertgroup is in use
		List<CriticalAlertList> list = criticalAlertListRepository.findByAlertGroupId(id);
		if(list.size() > 0 ){
			return 0;
		}else{
			alertGroupRepository.deleteById(id);
			return id;
		}
	}

	@Override
	public List<AlertGroup> findByDataType(DataType dataType) {
	return 	alertGroupRepository.findByDataType(dataType);

	}
}
