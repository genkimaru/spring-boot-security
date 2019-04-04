package com.ccsip.coap.master.metadata.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccsip.coap.master.metadata.domain.metadata.WebSitePulse;
import com.ccsip.coap.master.metadata.repository.WebSitePulseRepository;

@Component("webSitePulseService")
@Transactional
public class WebSitePulseServiceImpl implements WebSitePulseService {
	private final WebSitePulseRepository webSitePulseRepository;

	public WebSitePulseServiceImpl(WebSitePulseRepository webSitePulseRepository) {
		this.webSitePulseRepository = webSitePulseRepository;
	}

	@Override
	public List<WebSitePulse> findAll() {
		return (List<WebSitePulse>) webSitePulseRepository.findAll();
	}

}
