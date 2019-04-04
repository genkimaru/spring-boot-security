package com.ccsip.coap.master.metadata.dbunit;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccsip.coap.master.metadata.domain.confdata.DataSource;
import com.ccsip.coap.master.metadata.service.DataSourceService;

public class DataSourceServiceTest extends DBUnitBase  {

	@Autowired 
	DataSourceService dataSourceService;

	private  String[] testTable = new String[]{"cf_data_source"};

	private String select_sql = "select  name from cf_data_source";
	
	private String save_expect_path = "/dataSource/save_expect.xml";
	
	private String update_origin_path = "/dataSource/update_origin.xml";
	
	private String update_expect_path = "/dataSource/update_expect.xml";
	
	private String remove_origin_path = "/dataSource/remove_origin.xml";
	
	private String find_origin_path = "/dataSource/find_origin.xml";

	public DataSourceServiceTest() {
		super();
		this.table = testTable;
	}

	@Test  
    public void testSave() throws Exception {  
    	clearTable(table);
		DataSource dataSource = new DataSource();
		dataSource.setName("Scom");
        dataSource = dataSourceService.save(dataSource);  
        verifyDataSet(table[0] , select_sql , save_expect_path);
     
    }  
  
    @Test  
    public void testUpdate() throws Exception { 
    
    	cleanAndInsert(update_origin_path); 
        
        List<DataSource> dataSources = dataSourceService.findAll();
        for(DataSource s : dataSources){
    		s.setName("Scom_updated");
    		dataSourceService.update(s);
        }

        verifyDataSet(table[0] , select_sql , update_expect_path);
    }  
  
    @Test  
    public void testRemove() throws Exception {  
    	
    	cleanAndInsert(remove_origin_path);
        
        List<DataSource> dataSources = dataSourceService.findAll();
        for(DataSource s : dataSources){

            dataSourceService.delete(s);
        }
        
        verifyEmpty(table[0]);
  
          
    }  
      
    @Test  
    public void testFindAll() throws Exception {  
    	cleanAndInsert(find_origin_path); 
  
        List<DataSource> findAll = dataSourceService.findAll(); 
  
        Assert.assertEquals(3, findAll.size());  
    }

	@Override
	public void setConf() {
		// TODO Auto-generated method stub
		
	}  

}
