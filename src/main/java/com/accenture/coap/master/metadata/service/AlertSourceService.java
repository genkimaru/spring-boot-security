/*
 * Copyright 2012-2013 the original author or authors.
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

package com.accenture.coap.master.metadata.service;

import java.util.List;

import com.accenture.coap.master.dto.AlertSourceFormDto;
import com.accenture.coap.master.metadata.domain.confdata.AlertSource;

public interface AlertSourceService {

	AlertSource save(AlertSource alertSource);

	int update(AlertSourceFormDto as);

	int update(AlertSource alertSource);

	void delete(Long asId);

	void deleteByAirId(Long airid);

	List<AlertSource> findBy();

	AlertSource findByName(String name);

	AlertSource findOne(long id);

	/**
	 *
	 * @param airid
	 *            初始化该AIRID下所有DataType的AlertSource
	 * @return
	 */
	List<AlertSource> initAlertSource(Long airid);

	List<AlertSource> findByAirId(long airId);

	List<AlertSource> findByAirId2(long airId);

	List<AlertSource> findByAirIdAndParentIsNull(long airId);

	List<AlertSource> findByAirIdAndDataTypeIdAndParentIsNull(Long airId, Long dataTypeId);

	List<AlertSource> findAllParentIsNull();

}
