package com.ccsip.coap.master.metadata.service;

import java.util.ArrayList;
import java.util.List;

import com.ccsip.coap.master.metadata.repository.ScomMetricRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccsip.coap.master.metadata.domain.metadata.ScomMetric;

@Component("scomMetricService")
@Transactional
public class ScomMetricServiceImpl implements ScomMetricService {

	private final ScomMetricRepository scomMetricRepository;

	public ScomMetricServiceImpl(ScomMetricRepository scomMetricRepository) {
		this.scomMetricRepository = scomMetricRepository;
	}

	@Override
	public List<ScomMetric> findAllKeyIsNull() {
		List<ScomMetric> res = new ArrayList<ScomMetric>();
		List<Object[]> list = scomMetricRepository.getScomMetric();
		for (Object[] e : list) {
			res.add(new ScomMetric(null, (String) e[0], (String) e[1]));
		}
		return res;
	}

}
