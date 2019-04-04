package com.ccsip.coap.master.metadata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ccsip.coap.master.dto.GetCriticalAlertList;
import com.ccsip.coap.master.metadata.domain.confdata.AlertGroup;
import com.ccsip.coap.master.metadata.domain.confdata.CriticalAlertList;
import com.ccsip.coap.master.metadata.domain.confdata.ExclusionList;
import com.ccsip.coap.master.metadata.domain.confdata.InclusionList;
import com.ccsip.coap.master.metadata.domain.metadata.Alert;
import com.ccsip.coap.master.metadata.service.CriticalAlertListService;
import com.ccsip.coap.master.metadata.service.ServerService;
import com.alibaba.fastjson.JSON;

/**
 * Test CRUD of the Service{@link ServerService}.
 * 
 * @author guan.wang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CriticalAlertListServiceTests {

	@Autowired
	CriticalAlertListService criticalAlertListService;

	@Before
	public void setUp() {

	}

	@Test
	public void testInclusionListSave() {
		CriticalAlertList criticalAlertList = new InclusionList();
		AlertGroup alertGroup = new AlertGroup();
		alertGroup.setId(1L);
		criticalAlertList.setAlertGroup(alertGroup);
		Set<Alert> additionalAlerts = new HashSet<Alert>();
		additionalAlerts.add(new Alert(7L));
		additionalAlerts.add(new Alert(8L));
		criticalAlertList.setAdditionalAlerts(additionalAlerts);
		criticalAlertListService.save(criticalAlertList);

	}

	@Test
	public void testExclusionListSave() {
		CriticalAlertList criticalAlertList = new ExclusionList();
		AlertGroup alertGroup = new AlertGroup();
		alertGroup.setId(2L);
		criticalAlertList.setAlertGroup(alertGroup);
		Set<Alert> additionalAlerts = new HashSet<Alert>();
		additionalAlerts.add(new Alert(7L));
		additionalAlerts.add(new Alert(8L));
		criticalAlertList.setAdditionalAlerts(additionalAlerts);
		criticalAlertListService.save(criticalAlertList);

	}

	@Test
	public void testfindCriticalAlertList() {
		CriticalAlertList criticalAlertList = criticalAlertListService.findById(27L);
		if (criticalAlertList instanceof ExclusionList) {
			System.out.println("ExclusionList");
		} else {
			System.out.println("InclusionList");
		}
		String cal = JSON.toJSONString(criticalAlertList);
		System.out.println(cal);
	}

	@Test
	public void testfindDtype() {
		List<GetCriticalAlertList> map = criticalAlertListService.findByDType("InclusionList");
		System.out.println(map);
	}
}
