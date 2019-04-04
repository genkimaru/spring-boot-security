package com.accenture.coap.master.metadata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.accenture.coap.master.metadata.domain.metadata.ScomMetric;

public interface ScomMetricRepository extends PagingAndSortingRepository<ScomMetric, Long> {

	@Query(value = "select distinct metric.KEY,metric.NAME from MD_SCOM_METRIC metric where metric.KEY != ''", nativeQuery = true)
	public List<Object[]> getScomMetric();
}
