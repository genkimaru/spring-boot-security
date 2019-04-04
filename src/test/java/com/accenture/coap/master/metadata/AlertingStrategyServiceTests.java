//package com.accenture.coap.master.metadata;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.accenture.coap.master.metadata.domain.confdata.AlertingStrategy;
//import com.accenture.coap.master.metadata.domain.confdata.StatisticalAlertingStrategy;
//import com.accenture.coap.master.metadata.service.AlertingStrategyService;
//import com.accenture.coap.master.metadata.service.ServerService;
//
///**
// * Test CRUD of the Service{@link ServerService}.
// * 
// * @author guan.wang
// */
//@RunWith(SpringRunner.class)
//@Transactional
//@SpringBootTest
//public class AlertingStrategyServiceTests {
//
//	@Autowired
//	AlertingStrategyService alertingStrategyService;
//
//	StatisticalAlertingStrategy asa;
//
//	@Test
//	public void testSave() {
//		StatisticalAlertingStrategy asa = new StatisticalAlertingStrategy();
//		asa.setAirId(123L);
//		asa.setRedThreshold(123);
//		asa.setAmberAboveThreshold(123);
//		asa.setName("as");
//		AlertingStrategy save = alertingStrategyService.save(asa);
//		Assert.assertEquals(save.getId(), alertingStrategyService.findStrategyById(save.getId()).getId());
//	}
//	
//	@Test
//	public void testIsCAS() {
//		StatisticalAlertingStrategy asa = new StatisticalAlertingStrategy();
//		asa.setAirId(123L);
//		asa.setRedThreshold(123);
//		asa.setAmberAboveThreshold(123);
//		asa.setName("as");
//		AlertingStrategy save = alertingStrategyService.save(asa);
//		Assert.assertEquals(false, alertingStrategyService.isCAS(save.getId()));
//	}
//
//}
