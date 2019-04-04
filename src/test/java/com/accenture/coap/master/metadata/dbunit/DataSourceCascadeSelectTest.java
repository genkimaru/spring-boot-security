package com.accenture.coap.master.metadata.dbunit;
/*
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.accenture.coap.master.metadata.domain.confdata.DataSource;
import com.accenture.coap.master.metadata.service.DataSourceService;

public class DataSourceCascadeSelectTest extends DBUnitBase  {

	@Autowired 
	DataSourceService dataSourceService;

	private  String testTable = "cf_data_source";

	private String select_sql = "select  name from cf_data_source";
	
	private String find_origin_path = "/cascade/select-datasource-alert.xml";

	public DataSourceCascadeSelectTest() {
		super();
		this.table = testTable;
	}

	@Test  
    public void testCascadeSelect() throws Exception {  
    	cleanAndInsert(find_origin_path);

        DataSource  dataSource = dataSourceService.findOne(1l); 
        
        Assert.assertEquals(2,dataSource.getDataTypes().size());
     
    }  
 
}
*/