package com.accenture.coap.master.metadata.service;

import java.util.List;
import java.util.Map;

import com.accenture.coap.master.metadata.domain.metadata.ScomMetric;

public interface ScomMetricService {

	List<ScomMetric> findAllKeyIsNull();
}
