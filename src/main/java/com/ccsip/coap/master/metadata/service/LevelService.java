package com.ccsip.coap.master.metadata.service;

import java.util.List;

import com.ccsip.coap.master.metadata.domain.confdata.Level;

public interface LevelService {

	List<Level> findAll();
	
}
