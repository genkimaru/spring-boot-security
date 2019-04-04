package com.ccsip.coap.master.metadata.dbunit;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccsip.coap.master.metadata.domain.confdata.DataType;
import com.ccsip.coap.master.metadata.service.DataTypeService;

public class DataTypeServiceTest extends DBUnitBase  {

	@Autowired 
	DataTypeService dataTypeService;

	private  String[] testTable = new String[]{"cf_data_type"};

	private String select_sql = "select  name , target_entity , target_table from cf_data_type";
	
	private String save_expect_path = "/dataType/save_expect.xml";
	
	private String update_origin_path = "/dataType/update_origin.xml";
	
	private String update_expect_path = "/dataType/update_expect.xml";
	
	private String remove_origin_path = "/dataType/remove_origin.xml";
	
	private String find_origin_path = "/dataType/find_origin.xml";

	public DataTypeServiceTest() {
		super();
		this.table = testTable;
	}

	@Test  
    public void testSave() throws Exception {  
//    	clearTable(table);
		DataType dataType = new DataType();
		dataType.setName("Scom_metric");
		dataType.setTargetEntity("TestEntity");
		dataType.setTargetTable("test_table");
        dataType = dataTypeService.save(dataType);  
        verifyDataSet(table[0] , select_sql , save_expect_path);
     
    }  
  
    @Test  
    public void testUpdate() throws Exception { 
    
    	cleanAndInsert(update_origin_path); 
        
        List<DataType> dataTypes = dataTypeService.findAll();
        for(DataType s : dataTypes){
    		s.setName("Scom_metric_updated");
    		dataTypeService.update(s);
        }

        verifyDataSet(table[0] , select_sql , update_expect_path);
    }  
  
    @Test  
    public void testRemove() throws Exception {  
    	
    	cleanAndInsert(remove_origin_path);
        
        List<DataType> dataTypes = dataTypeService.findAll();
        for(DataType s : dataTypes){

            dataTypeService.delete(s);
        }
        
        verifyEmpty(table[0]);
  
          
    }

	@Override
	public void setConf() {
		// TODO Auto-generated method stub
		
	}  
      
   
}
