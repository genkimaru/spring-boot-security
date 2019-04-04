/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by Alertlicable law or agreed to in writing, software
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

import com.accenture.coap.master.metadata.domain.confdata.DataType;
import com.accenture.coap.master.metadata.domain.metadata.Alert;
import com.accenture.coap.master.metadata.repository.AlertRepository;

@Component("alertService")
@Transactional
public class AlertServiceImpl implements AlertService {

	private final AlertRepository alertRepository;

	public AlertServiceImpl(AlertRepository alertRepository) {
		this.alertRepository = alertRepository;
	}

	@Override
	public List<Alert> findAll() {
		 Iterable<Alert> Alerts = alertRepository.findAll();
		 List<Alert> list = new ArrayList<Alert>();
		 Alerts.forEach(list::add);
		 return list;
	}

	@Override
	public Alert save(Alert alert) {
		return alertRepository.save(alert);
	}

	@Override
	public Iterable<Alert> save(Iterable<Alert> alerts) {
		return alertRepository.save(alerts);
	}

	@Override
	public Alert findOne(long id) {
		return alertRepository.findOne(id);
	}

	@Override
	public Alert update(Alert alert) {
		return alertRepository.save(alert);
		 
	}

	@Override
	public void delete(Alert alert) {
		alertRepository.delete(alert);
		
	}
	
	@Override
	public List<Alert> findByDataType(DataType dataType) {
		return alertRepository.findByDataType(dataType);
	}
}
