package com.accenture.coap.master.metadata.service;

import java.util.List;
import java.util.Map;

import com.accenture.coap.master.metadata.domain.metadata.ScomMetric;
import com.accenture.coap.master.metadata.domain.metadata.WebSitePulse;

public interface WebSitePulseService {

	List<WebSitePulse> findAll();
}
