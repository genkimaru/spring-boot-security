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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.CrudRepository;

import com.accenture.coap.master.dto.GetServer;
import com.accenture.coap.master.metadata.domain.metadata.App;

public interface AppRepository extends CrudRepository<App, Long> {

	Page<App> findAll(Pageable pageable);

	App findByNameAllIgnoringCase(String name);

	List<App> findByAirIdOrNameLike(Long airId, String appName);

	@EntityGraph(value = "App.detail", type = EntityGraphType.FETCH)
	App findByAirId(Long airId);

	@Query(value = "SELECT s.AIR_ID,s.NAME as hostname,c.NAME as function FROM MD_SERVER s left join MD_COMPONENT c on s.COMPONENT_ID=c.ID where c.NAME is not null", nativeQuery = true)
	List<Object[]> getServer();

}
