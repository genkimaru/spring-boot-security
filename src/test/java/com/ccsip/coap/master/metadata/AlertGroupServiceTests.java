package com.ccsip.coap.master.metadata;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ccsip.coap.master.metadata.domain.confdata.AlertGroup;
import com.ccsip.coap.master.metadata.domain.metadata.Alert;
import com.ccsip.coap.master.metadata.service.AlertGroupService;

/**
 * Test CRUD of the Service{@link AlertGroupService}.
 * 
 * @author guan.wang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
// @Transactional
public class AlertGroupServiceTests {

	@Autowired
	AlertGroupService alertGroupService;

	AlertGroup alertGroup;

	Alert alert;

	@Before
	public void setUp() {
		alertGroup = new AlertGroup();
		alertGroup.setName("alert group ");
		alert = new Alert();
		alert.setSeverity("red");
		alert.setName("alert name");
//		alert.setAlertGroups(new HashSet<AlertGroup>() {
//			{
//				add(alertGroup);
//			}
//		});
		alertGroup.setAlerts(new HashSet<Alert>() {
			{
				add(alert);
			}
		});
	}

	/**
	 * test many to many relationship of AlertGroup and Alert
	 */
	@Test
	public void testSave() {
		alertGroup = alertGroupService.save(alertGroup);

		assertEquals(alertGroupService.findOne(alertGroup.getId()).getId(), alertGroup.getId());
	}

}
