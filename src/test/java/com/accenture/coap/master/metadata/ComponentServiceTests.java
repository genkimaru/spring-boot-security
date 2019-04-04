package com.accenture.coap.master.metadata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.coap.master.metadata.domain.metadata.Server;
import com.accenture.coap.master.metadata.domain.metadata.Component;
import com.accenture.coap.master.metadata.service.ComponentService;

/**
 * Test CRUD of the Service{@link ComponentService}.
 * 
 * @author guan.wang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ComponentServiceTests {
	
	@Autowired
	ComponentService componentService;
	
	Component component;
	
	Server server;
	

	@Before
	public void setUp() {

		server = new Server();
		server.setAirId(2700l);
		server.setName("CTCVW6070");
		component = new Component();
		component.setAirId(2700l);
		component.setName("Web");
		server.setComponent(component);
	}

	@Test
	public void testSave() {
		component = componentService.save(component);
		
		assertEquals(componentService.findOne(component.getId()).getId() , component.getId());
	}
	
	@Test
	public void testUpdate() {
		component = componentService.save(component);
		
		assertEquals(componentService.findOne(component.getId()).getId() , component.getId());
		
		component.setName("Database");
		
		Component componentModified = componentService.update(component);
		
		assertEquals(component.getId() , componentModified.getId());
		
		assertEquals(componentModified.getName() , "Database");
	}
	
	
	@Test
	public void findAllTest() {
		component = componentService.save(component);
		assertEquals(componentService.findOne(component.getId()).getId() , component.getId());
		List<Component> components = componentService.findAll();
		assertEquals(components.size() , 1);
	}

	@Test
	public void testDelete() {
		component = componentService.save(component);
		assertEquals(componentService.findOne(component.getId()).getId() , component.getId());
		componentService.delete(component);
		assertNull(componentService.findOne(component.getId()));
		
	}

}

