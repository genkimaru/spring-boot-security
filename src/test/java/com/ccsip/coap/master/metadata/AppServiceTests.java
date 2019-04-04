package com.ccsip.coap.master.metadata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ccsip.coap.master.metadata.domain.metadata.App;
import com.ccsip.coap.master.metadata.service.AppService;

/**
 * Test CRUD of the Service{@link AppService}.
 * 
 * @author guan.wang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AppServiceTests {
	
	@Autowired 
	AppService appService;
	
	App app ;

	@Before
	public void setUp() {

		app = new App();
		app.setAirId(2700l);
		app.setName("MyTimeandExpenses");
		app.setServiceTier("NAO");
		
	}
	
	@Test
	public void testSave() {
		app = appService.save(app);
		
		assertEquals(appService.findOne(app.getId()).getId() , app.getId());
	}
	
	@Test
	public void testUpdate() {
		app = appService.save(app);
		
		assertEquals(appService.findOne(app.getId()).getId() , app.getId());
		
		app.setName("MyTE");
		
		App appModified = appService.update(app);
		
		assertEquals(app.getId() , appModified.getId());
		
		assertEquals(appModified.getName() , "MyTE");
	}
	
	
	@Test
	public void findAllTest() {
		app = appService.save(app);
		assertEquals(appService.findOne(app.getId()).getId() , app.getId());
		List<App> apps = appService.findAll();
		assertEquals(apps.size() , 1);
	}

	@Test
	public void testDelete() {
		app = appService.save(app);
		assertEquals(appService.findOne(app.getId()).getId() , app.getId());
		appService.delete(app);
		assertNull(appService.findOne(app.getId()));
		
	}

}

