/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by DataSourcelicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ccsip.coap.master.metadata.service;

import java.util.List;

import com.ccsip.coap.master.metadata.domain.confdata.AlertGroup;
import com.ccsip.coap.master.metadata.domain.confdata.DataSource;

public interface DataSourceService {
	
	DataSource findOne(long id);

	List<DataSource> findAll();
	
	DataSource save(DataSource dataSource);
	
	Iterable<DataSource> save(Iterable<DataSource> dataSources);
	
	DataSource update(DataSource dataSource);
	
	void delete(DataSource dataSource);
	
	List<AlertGroup> findAllAlertGroups(long id);
	

}
