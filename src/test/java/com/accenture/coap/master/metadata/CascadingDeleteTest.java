package com.accenture.coap.master.metadata;

import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.coap.master.metadata.domain.metadata.App;
import com.accenture.coap.master.metadata.domain.metadata.Server;
import com.accenture.coap.master.metadata.domain.metadata.Component;
import com.accenture.coap.master.metadata.service.AppService;
import com.accenture.coap.master.metadata.service.ComponentService;
import com.accenture.coap.master.metadata.service.ServerService;

/**
 * Test cascading delete  of the Service{@link AppService}.
 * 
 * @author guan.wang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CascadingDeleteTest {
	
	@Autowired 
	AppService appService;
	
	@Autowired
	ComponentService componentService;
	
	@Autowired 
	ServerService serverService;

	 App app = null;

	 Component component = null;

	 Server server = null;
	

	@Before
	public void setUp() {

		app = new App();
		component = new Component();
		server = new Server();
		
		app.setAirId(2700l);
		app.setName("MyTimeandExpenses");
		app.setServiceTier("NAO");
		app.setComponents(new HashSet<Component>(){{
			add(component);
		}});
		
		component.setAirId(2700l);
		component.setName("Web");
		component.setApp(app);
		component.setServers(new HashSet<Server>(){{
			add(server);
		}});
		
		server.setAirId(2700l);
		server.setName("CDCE60002");
		server.setComponent(component);
		

	}
	


	@Test
	public void testDelete() {
		 server = serverService.save(server);
		 component = componentService.save(component);
		 app = appService.save(app);
		
		appService.delete(app);
		
		assertNull(appService.findOne(app.getId()));
		assertNull(componentService.findOne(component.getId()));
		assertNull(serverService.findOne(server.getId()));
		
	}

}

