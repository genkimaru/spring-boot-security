package com.ccsip.coap.master.metadata;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ccsip.coap.master.metadata.domain.confdata.DataType;
import com.ccsip.coap.master.metadata.domain.metadata.Alert;
import com.ccsip.coap.master.metadata.service.AlertGroupService;
import com.ccsip.coap.master.metadata.service.AlertService;
import com.ccsip.coap.master.metadata.service.DataTypeService;

import junit.framework.Assert;

/**
 * Test CRUD of the Service{@link AlertGroupService}.
 * 
 * @author guan.wang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
// @Transactional
public class AlertServiceTests {
	
	@Autowired 
	AlertService alertService;
	
	@Autowired
	DataTypeService dataTypeService;
	

	@Test
	public void testFindByDataType() {
		DataType dataType = dataTypeService.findOne(1);
		List<Alert> alerts = alertService.findByDataType(dataType);
		Assert.assertEquals(3, alerts.size());
	}

	
	
	

}

