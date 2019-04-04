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

package com.ccsip.coap.master.metadata.service;

import java.util.List;

import com.ccsip.coap.master.dto.GetServer;
import com.ccsip.coap.master.metadata.domain.metadata.App;

public interface AppService {

	App findOne(long id);

	List<App> findAll();

	Iterable<App> findAllOnEager();

	App save(App entity);

	Iterable<App> save(Iterable<App> entities);

	void delete(App app);

	App update(App app);

	List<App> findByAirIdOrAppNameLike(Long airId, String appName);

	App findByAirId(Long airId);

	List<GetServer> getServer();

}
