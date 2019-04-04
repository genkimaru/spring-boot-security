package com.accenture.coap.master.metadata.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.coap.master.metadata.domain.confdata.Level;
import com.accenture.coap.master.metadata.repository.LevelRepository;
@Component("levelService")
@Transactional
public class LevelServiceImpl implements LevelService{
	
	private final LevelRepository levelRepository;

	public LevelServiceImpl(LevelRepository levelRepository) {
		this.levelRepository = levelRepository;
	}

	@Override
	public List<Level> findAll() {
		Iterable<Level> levels = levelRepository.findAll();
		 List<Level> list = new ArrayList<Level>();
		 levels.forEach(list::add);
		 return list;
	}

}
