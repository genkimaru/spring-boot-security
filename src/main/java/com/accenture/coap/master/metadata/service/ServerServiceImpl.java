/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by serverlicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.accenture.coap.master.metadata.service;

import com.accenture.coap.master.metadata.domain.metadata.Server;
import com.accenture.coap.master.metadata.repository.ServerRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("serverService")
@Transactional
public class ServerServiceImpl implements ServerService {

	private final ServerRepository serverRepository;

	public ServerServiceImpl(ServerRepository serverRepository) {
		this.serverRepository = serverRepository;
	}

	@Override
	public List<Server> findAll() {
		 Iterable<Server> servers = serverRepository.findAll();
		 List<Server> list = new ArrayList<Server>();
		 servers.forEach(list::add);
		 return list;
	}

	@Override
	public Server save(Server server) {
		return serverRepository.save(server);
	}

	@Override
	public Iterable<Server> save(Iterable<Server> servers) {
		return serverRepository.save(servers);
	}

	@Override
	public Server findOne(long id) {
		return serverRepository.findOne(id);
	}

	@Override
	public Server update(Server server) {
		return serverRepository.save(server);
		 
	}

	@Override
	public void delete(Server server) {
		serverRepository.delete(server);
		
	}
}
