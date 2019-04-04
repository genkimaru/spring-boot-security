/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by dataTypelicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.accenture.coap.master.metadata.service;

import com.accenture.coap.master.metadata.domain.confdata.DataType;
import com.accenture.coap.master.metadata.repository.DataTypeRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("dataTypeService")
@Transactional
public class DataTypeServiceImpl implements DataTypeService {

	private final DataTypeRepository dataTypeRepository;

	public DataTypeServiceImpl(DataTypeRepository dataTypeRepository) {
		this.dataTypeRepository = dataTypeRepository;
	}

	@Override
	public List<DataType> findAll() {
		Iterable<DataType> dataTypes = dataTypeRepository.findAll();
		List<DataType> list = new ArrayList<DataType>();
		dataTypes.forEach(list::add);
		return list;
	}

	@Override
	public List<DataType> findAllWithAlert() {
		Iterable<DataType> dataTypes = dataTypeRepository.findAll();
		List<DataType> list = new ArrayList<DataType>();
		for (DataType dataType : dataTypes) {
			if (dataType.getIsAlert()) {
				list.add(dataType);
			}
		}
		return list;
	}

	@Override
	public DataType save(DataType dataType) {
		return dataTypeRepository.save(dataType);
	}

	@Override
	public Iterable<DataType> save(Iterable<DataType> dataTypes) {
		return dataTypeRepository.save(dataTypes);
	}

	@Override
	public DataType findOne(long id) {
		return dataTypeRepository.findOne(id);
	}

	@Override
	public DataType update(DataType dataType) {
		return dataTypeRepository.save(dataType);

	}

	@Override
	public void delete(DataType dataType) {
		dataTypeRepository.delete(dataType);

	}

}
