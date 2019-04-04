package com.accenture.coap.master.metadata.service;

import java.util.ArrayList;
import java.util.List;

import com.accenture.coap.master.metadata.domain.confdata.Level;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.coap.master.metadata.repository.InfrastructureUnitRepository;

@Component("infrastructureUnitService")
@Transactional
public class InfrastructureUnitServiceImpl implements InfrastructureUnitService {

	private final InfrastructureUnitRepository infrastructureUnitRepository;

	public InfrastructureUnitServiceImpl(InfrastructureUnitRepository infrastructureUnitRepository) {
		this.infrastructureUnitRepository = infrastructureUnitRepository;
	}
	
	@Override
	public Level findOne(long id) {
		return infrastructureUnitRepository.findOne(id);
	}

	@Override
	public List<Level> findAll() {
		 Iterable<Level> units = infrastructureUnitRepository.findAll();
		 List<Level> list = new ArrayList<Level>();
		 units.forEach(list::add);
		 return list;
	}

}
