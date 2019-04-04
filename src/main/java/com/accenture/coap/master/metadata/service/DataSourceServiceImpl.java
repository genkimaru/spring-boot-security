/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by dataSourcelicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.accenture.coap.master.metadata.service;

import com.accenture.coap.master.metadata.domain.confdata.AlertGroup;
import com.accenture.coap.master.metadata.domain.confdata.DataSource;
import com.accenture.coap.master.metadata.domain.confdata.DataType;
import com.accenture.coap.master.metadata.repository.DataSourceRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("dataSourceService")
@Transactional
public class DataSourceServiceImpl implements DataSourceService {

	private final DataSourceRepository dataSourceRepository;

	public DataSourceServiceImpl(DataSourceRepository dataSourceRepository) {
		this.dataSourceRepository = dataSourceRepository;
	}

	@Override
	public List<DataSource> findAll() {
		 Iterable<DataSource> dataSources = dataSourceRepository.findAll();
		 List<DataSource> list = new ArrayList<DataSource>();
		 dataSources.forEach(list::add);
		 return list;
	}

	@Override
	public DataSource save(DataSource dataSource) {
		return dataSourceRepository.save(dataSource);
	}

	@Override
	public Iterable<DataSource> save(Iterable<DataSource> dataSources) {
		return dataSourceRepository.save(dataSources);
	}

	@Override
	public DataSource findOne(long id) {
		return dataSourceRepository.findOne(id);
	}

	@Override
	public DataSource update(DataSource dataSource) {
		return dataSourceRepository.save(dataSource);
		 
	}

	@Override
	public void delete(DataSource dataSource) {
		dataSourceRepository.delete(dataSource);
		
	}

	@Override
	public List<AlertGroup> findAllAlertGroups(long id) {
		List<AlertGroup> groups = new ArrayList<AlertGroup>();
//		DataSource dataSource = dataSourceRepository.findOne(id);
//		List<DataType> dataTypes = dataSource.getDataTypes();
//		
//		for(DataType dataType : dataTypes){
//			if(dataType.getIsAlert()){
//				List<AlertGroup> alertGroups = dataType.getAlertGroups();
//				groups.addAll(alertGroups);
//			}
//			
//		}
		return groups;
	}
	
	
}
