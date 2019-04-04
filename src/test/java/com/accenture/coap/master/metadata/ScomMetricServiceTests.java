package com.accenture.coap.master.metadata;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.accenture.coap.master.metadata.domain.metadata.ScomMetric;
import com.accenture.coap.master.metadata.service.ScomMetricService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScomMetricServiceTests {

	@Autowired
	ScomMetricService scomMetricService;
	
	@Test
	public void testfindAllKeyIsNull() {
		List<ScomMetric> scomMetricMap = scomMetricService.findAllKeyIsNull();
		assertEquals(scomMetricMap.size() , 5);
	}
	
}
