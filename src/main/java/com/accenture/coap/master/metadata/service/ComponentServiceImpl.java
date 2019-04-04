/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by componentlicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.accenture.coap.master.metadata.service;

import com.accenture.coap.master.metadata.domain.metadata.Component;
import com.accenture.coap.master.metadata.repository.ComponentRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Component("componentService")
@Transactional
public class ComponentServiceImpl implements ComponentService {

	private final ComponentRepository componentRepository;

	public ComponentServiceImpl(ComponentRepository componentRepository) {
		this.componentRepository = componentRepository;
	}

	@Override
	public Component findOne(long id) {
		return componentRepository.findOne(id);
	}
	
	@Override
	public List<Component> findAll() {
		
		 Iterable<Component> components = componentRepository.findAll();
		 List<Component> list = new ArrayList<Component>();
		 components.forEach(list::add);
		 return list;
	}

	
	@Override
	public Component save(Component component) {
		return componentRepository.save(component);
	}

	@Override
	public Iterable<Component> save(Iterable<Component> components) {
		return componentRepository.save(components);
	}

	@Override
	public Component update(Component component) {
		return componentRepository.save(component);
	}

	@Override
	public void delete(Component component) {
		
		componentRepository.delete(component);
	}


}
