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

import com.accenture.coap.master.metadata.domain.metadata.App;
import com.accenture.coap.master.metadata.domain.metadata.Server;
import com.accenture.coap.master.metadata.service.ServerService;

/**
 * Test CRUD of the Service{@link ServerService}.
 * 
 * @author guan.wang
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class ServerServiceTests {

	@Autowired 
	ServerService serverService;
	
	Server server;
	App app;

	@Before
	public void setUp() {

		server = new Server();
		server.setAirId(2700l);
		server.setName("CTCVW6070");
	}
	
	
	@Test
	public void testSave() {
		server = serverService.save(server);
		
		assertEquals(serverService.findOne(server.getId()).getId() , server.getId());
	}
	
	@Test
	public void testUpdate() {
		server = serverService.save(server);
		
		assertEquals(serverService.findOne(server.getId()).getId() , server.getId());
		
		server.setName("CDFD2929");
		
		Server serverModified = serverService.update(server);
		
		assertEquals(server.getId() , serverModified.getId());
		
		assertEquals(serverModified.getName() , "CDFD2929");
	}
	
	
	@Test
	public void findAllTest() {
		server = serverService.save(server);
		assertEquals(serverService.findOne(server.getId()).getId() , server.getId());
		List<Server> servers = serverService.findAll();
		assertEquals(servers.size() , 1);
	}

	@Test
	public void testDelete() {
		server = serverService.save(server);
		assertEquals(serverService.findOne(server.getId()).getId() , server.getId());
		serverService.delete(server);
		assertNull(serverService.findOne(server.getId()));
		
	}


}

