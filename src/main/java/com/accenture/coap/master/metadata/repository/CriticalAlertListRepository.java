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

package com.accenture.coap.master.metadata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.accenture.coap.master.metadata.domain.confdata.CriticalAlertList;

public interface CriticalAlertListRepository extends CrudRepository<CriticalAlertList, Long> {

	List<CriticalAlertList> findById(Long airId);

	@Query(value = "SELECT cal.ID AS crititalAlertListId, so.ID FROM  CF_CRITICAL_ALERT_LIST cal LEFT JOIN  CF_ALERT_SOURCE so ON so.CRITICAL_ALERT_LIST_ID = cal.ID WHERE cal.LIST_TYPE = ?1 and so.ID is not null", nativeQuery = true)
	List<Object[]> findByDType(String dType);
	
	@Query(value = "select * from CF_CRITICAL_ALERT_LIST cal  where cal.ALERT_GROUP_ID = ?1 ", nativeQuery = true)
	List<CriticalAlertList> findByAlertGroupId(Long alertGroupId);

}
