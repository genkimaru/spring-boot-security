package com.ccsip.coap.master.metadata.dbunit;

import java.util.List;

import com.ccsip.coap.master.metadata.domain.metadata.Server;
import com.ccsip.coap.master.metadata.service.ServerService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ServerServiceTest extends DBUnitBase  {

	@Autowired
    ServerService serverService;

	private  String[] testTable = new String[]{"md_server"};

	private String select_sql = "select air_id , name from md_server";
	
	private String save_expect_path = "/server/save_expect.xml";
	
	private String update_origin_path = "/server/update_origin.xml";
	
	private String update_expect_path = "/server/update_expect.xml";
	
	private String remove_origin_path = "/server/remove_origin.xml";
	
	private String find_origin_path = "/server/find_origin.xml";


	public ServerServiceTest() {
		super();
		this.table = testTable;
	}

	@Test  
    public void testSave() throws Exception {  
    	clearTable(table);
		Server server = new Server();
		server.setAirId(2700l);
		server.setName("CTCVW6070");
        server = serverService.save(server);  
        verifyDataSet(table[0] , select_sql , save_expect_path);
     
    }  
  
    @Test  
    public void testUpdate() throws Exception { 
    
    	cleanAndInsert(update_origin_path ); 
        
        List<Server> servers = serverService.findAll();
        for(Server s : servers){
    		s.setAirId(2700l);
    		s.setName("bbb");
    		serverService.update(s);
        }

        verifyDataSet(table[0] , select_sql, update_expect_path);
    }  
  
    @Test  
    public void testRemove() throws Exception {  
    	
    	cleanAndInsert(remove_origin_path);
        
        List<Server> servers = serverService.findAll();
        for(Server s : servers){

            serverService.delete(s);
        }
        
        verifyEmpty(table[0]);
  
          
    }  
      
    @Test  
    public void testFindAll() throws Exception {  
    	cleanAndInsert(find_origin_path); 
  
        List<Server> findAll = serverService.findAll(); 
  
        Assert.assertEquals(3, findAll.size());  
    }

	@Override
	public void setConf() {
		// TODO Auto-generated method stub
		
	}  
   
}
