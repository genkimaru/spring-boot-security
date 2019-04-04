/*
 * Copyright 2012-2016 the original author or authors.
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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.coap.master.dto.GetServer;
import com.accenture.coap.master.metadata.domain.metadata.App;
import com.accenture.coap.master.metadata.repository.AlertSourceRepository;
import com.accenture.coap.master.metadata.repository.AppRepository;

@Component("appService")
@Transactional
public class AppServiceImpl implements AppService {

	private final AppRepository appRepository;
	private final AlertSourceRepository alertSourceRepository;

	public AppServiceImpl(AppRepository appRepository, AlertSourceRepository alertSourceRepository) {
		this.appRepository = appRepository;
		this.alertSourceRepository = alertSourceRepository;
	}

	@Override
	public App save(App app) {
		return appRepository.save(app);
	}

	@Override
	public Iterable<App> save(Iterable<App> apps) {
		return appRepository.save(apps);
	}

	@Override
	public App findOne(long id) {

		return appRepository.findOne(id);
	}

	@Override
	public List<App> findAll() {
		Iterable<App> apps = appRepository.findAll();
		List<App> list = new ArrayList<App>();
		apps.forEach(list::add);
		return list;
	}

	@Override
	public Iterable<App> findAllOnEager() {
		return appRepository.findAll();
	}

	@Override
	public void delete(App app) {
		appRepository.delete(app);
	}

	@Override
	public App update(App app) {
		return appRepository.save(app);
	}

	@Override
	public List<App> findByAirIdOrAppNameLike(Long airId, String appName) {
		List<App> list = appRepository.findByAirIdOrNameLike(airId, appName);
		return list;
	}

	@Override
	public App findByAirId(Long airId) {
		return appRepository.findByAirId(airId);
	}

	@Override
	public List<GetServer> getServer() {
		List<GetServer> res = new ArrayList<GetServer>();
		List<Object[]> list = appRepository.getServer();
		for (Object[] e : list) {
			res.add(new GetServer(((BigInteger) e[0]).longValue(), (String) e[1], (String) e[2]));
		}
		return res;
	}
}
