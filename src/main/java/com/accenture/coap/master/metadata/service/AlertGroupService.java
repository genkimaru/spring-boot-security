/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by AlertGrouplicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.accenture.coap.master.metadata.service;

import java.util.List;

import com.accenture.coap.master.metadata.domain.confdata.AlertGroup;
import com.accenture.coap.master.metadata.domain.confdata.DataType;



public interface AlertGroupService {
	
	AlertGroup findOne(long id);

	List<AlertGroup> findAll();
	
	AlertGroup save(AlertGroup alertGroup);
	
	Iterable<AlertGroup> save(Iterable<AlertGroup> alertGroups);
	
	AlertGroup update(AlertGroup alertGroup);
	
	void delete(AlertGroup alertGroup);
	
	void delete(long id);
	
	long deleteById(long id);
	
	List<AlertGroup> findByDataTypeIdAndName(AlertGroup alertGroup);
	
	List<AlertGroup> findByDataType(DataType dataType);
	
}
