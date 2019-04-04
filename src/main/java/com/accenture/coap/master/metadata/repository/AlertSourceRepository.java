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

import com.accenture.coap.master.metadata.domain.confdata.AlertSource;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AlertSourceRepository extends CrudRepository<AlertSource, Long> {

	Page<AlertSource> findAll(Pageable pageable);

	AlertSource findByNameAllIgnoringCase(String name);

	void deleteByAirId(Long airid);

	@Query("select a from AlertSource a where a.airId = :airId ")
	List<AlertSource> findByAirId(@Param("airId") long airId);

	@Query("select a from AlertSource a where a.airId = :airId and a.parent is null ")
	List<AlertSource> findByAirId2(@Param("airId") long airId);

	// 下面给metadata使用
	@Query("select a from AlertSource a join fetch a.dataType b where a.airId = :airId and a.parent is null and a.status='Effective'")
	List<AlertSource> findByAirIdAndParentIsNull(@Param("airId") long airId);

	@Query("select a from AlertSource a join fetch a.dataType b where a.airId = :airId and b.id = :dataTypeId and a.parent is null and a.status='Effective'")
	List<AlertSource> findByAirIdAndDataTypeIdAndParentIsNull(@Param("airId") long airId, @Param("dataTypeId") long dataTypeId);

	@Query("select a from AlertSource a where a.parent is null and a.status='Effective'")
	List<AlertSource> findAllAndParentIsNull();

}
