package com.accenture.coap.master.metadata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.accenture.coap.master.metadata.domain.confdata.AlertGroup;
import com.accenture.coap.master.metadata.domain.confdata.AlertSource;
import com.accenture.coap.master.metadata.domain.confdata.CriticalAlertList;
import com.accenture.coap.master.metadata.domain.confdata.DataType;
import com.accenture.coap.master.metadata.domain.confdata.ExclusionList;
import com.accenture.coap.master.metadata.domain.confdata.InclusionList;
import com.accenture.coap.master.metadata.domain.confdata.Level;
import com.accenture.coap.master.metadata.domain.confdata.StatisticalThreshold;
import com.accenture.coap.master.metadata.domain.metadata.Alert;
import com.accenture.coap.master.metadata.service.AlertGroupService;
import com.accenture.coap.master.metadata.service.AlertSourceService;
import com.accenture.coap.master.metadata.service.CriticalAlertListService;
import com.accenture.coap.master.metadata.service.DataTypeService;
import com.accenture.coap.master.metadata.service.InfrastructureUnitService;
import com.accenture.coap.master.utils.Contant;

/**
 * Test CRUD of the Service{@link AlertSourceService}.
 * 
 * The following tests are based on the data of coap-master-data.sql
 * 
 * @author guan.wang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
// @Transactional
public class AlertSourceServiceTests {

	@Autowired
	AlertSourceService alertSourceService;

	@Autowired
	DataTypeService dataTypeService;

	@Autowired
	CriticalAlertListService criticalAlertListService;

	@Autowired
	InfrastructureUnitService infrastructureUnitService;

	@Autowired
	AlertGroupService alertGroupService;

	@Test
	public void testFindByAirId() {
		List<AlertSource> alertSources = alertSourceService.findByAirId(2700);
		Assert.assertEquals(4, alertSources.size());
	}

	@Test
	public void testFindCritical() {
		List<AlertSource> list = alertSourceService.findByAirId(2700L);
		System.out.println(list.get(0).getChildren());
	}

	@Test
	public void testFindByAirIdAndDataTypeId() {
		List<AlertSource> alertSources = alertSourceService.findByAirIdAndDataTypeIdAndParentIsNull(2700L, 1L);
		Assert.assertEquals(4, alertSources.size());
	}

	@Test
	public void testFindOne() {
		AlertSource alertSource = alertSourceService.findOne(1);
		Assert.assertEquals(1, alertSource.getChildren());
		Set<AlertSource> children = alertSource.getChildren();
		Assert.assertEquals(1, children.toArray(new AlertSource[1])[1].getChildren().size());
	}

	@Test
	public void testExtCriticalSave() {
		AlertSource as = new AlertSource();
		as.setAirId(1964L);
		as.setName("myScheduling-WebServerGroup-CriticalAlertingStrategy");
		as.setDataType(new DataType(1L));
		as.setStatus(Contant.DRAFT);
		CriticalAlertList criticalAlertList = new ExclusionList();
		AlertGroup alertGroup = new AlertGroup();
		alertGroup.setId(2L);
		criticalAlertList.setAlertGroup(alertGroup);
		Set<Alert> additionalAlerts = new HashSet<Alert>();
		additionalAlerts.add(new Alert(7L));
		additionalAlerts.add(new Alert(8L));
		criticalAlertList.setAdditionalAlerts(additionalAlerts);
		as.setCriticalAlertList(criticalAlertList);
		as.setRefId(1L);
		as.setLevel(new Level(1L));
		as.setStatisticalThreshold(new StatisticalThreshold());
		alertSourceService.save(as);

	}

	@Test
	public void testSave() {

		AlertSource appAlertSource = new AlertSource();
		appAlertSource.setAirId(2800l);
		appAlertSource.setDataType(dataTypeService.findOne(1));
		appAlertSource.setLevel(infrastructureUnitService.findOne(1l));
		appAlertSource.setRefId(1L);
		appAlertSource.setName("TestAppLEVEL");
		CriticalAlertList criticalAlertList = new InclusionList();
		criticalAlertList.setAlertGroup(alertGroupService.findOne(1));
		appAlertSource.setCriticalAlertList(criticalAlertList);

		AlertSource compnentAlertSource = new AlertSource();
		compnentAlertSource.setAirId(2800l);
		compnentAlertSource.setDataType(dataTypeService.findOne(1));
		compnentAlertSource.setLevel(infrastructureUnitService.findOne(2l));
		compnentAlertSource.setRefId(1L);
		compnentAlertSource.setName("TestCompnentLevel");
		CriticalAlertList criticalAlertList2 = new InclusionList();
		criticalAlertList2.setAlertGroup(alertGroupService.findOne(2));
		compnentAlertSource.setCriticalAlertList(criticalAlertList2);

		AlertSource serverAlertSource = new AlertSource();
		serverAlertSource.setAirId(2800l);
		serverAlertSource.setDataType(dataTypeService.findOne(1));
		serverAlertSource.setLevel(infrastructureUnitService.findOne(3l));
		serverAlertSource.setRefId(1L);
		serverAlertSource.setName("TestServerLevel");
		CriticalAlertList criticalAlertList3 = new InclusionList();
		criticalAlertList3.setAlertGroup(alertGroupService.findOne(3));
		serverAlertSource.setCriticalAlertList(criticalAlertList3);

		appAlertSource.setChildren(new HashSet() {
			{
				add(compnentAlertSource);
			}
		});

		compnentAlertSource.setParent(appAlertSource);

		compnentAlertSource.setChildren(new HashSet() {
			{
				add(serverAlertSource);
			}
		});
		serverAlertSource.setParent(compnentAlertSource);

		// alertSourceService.save(compnentAlertSource);
		alertSourceService.save(appAlertSource);
		// alertSourceService.save(serverAlertSource);

		List<AlertSource> alertSources = alertSourceService.findByAirId(2800);
		System.out.println(alertSources.size());
		Assert.assertEquals(3, alertSources.size());
	}

	@Test
	public void testDel() {
		alertSourceService.deleteByAirId(2700L);
	}
}
