package com.ccsip.coap.master.metadata.service;

import java.util.List;

import com.ccsip.coap.master.metadata.domain.metadata.ScomMetric;

public interface ScomMetricService {

	List<ScomMetric> findAllKeyIsNull();
}
