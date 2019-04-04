package com.accenture.coap.master.metadata.dbunit;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.accenture.coap.master.metadata.domain.confdata.AlertGroup;
import com.accenture.coap.master.metadata.domain.confdata.DataSource;
import com.accenture.coap.master.metadata.domain.confdata.DataType;
import com.accenture.coap.master.metadata.domain.metadata.Alert;
import com.accenture.coap.master.metadata.service.AlertGroupService;
import com.accenture.coap.master.metadata.service.AlertService;
import com.accenture.coap.master.metadata.service.DataSourceService;
import com.accenture.coap.master.metadata.service.DataTypeService;

/***
 *  使用dbunit进行AlertGroup等多表关联时候的查询或者删除
 * @author guan.c.wang
 *
 */
public class AlertGroupServiceTest extends DBUnitBase  {

	@Autowired 
	AlertService alertService;
	
	@Autowired
	DataTypeService dataTypeService;
	
	@Autowired 
	AlertGroupService alertGroupService;

	private  String[] tables = new String[]{"cf_data_source","cf_data_type","cf_alert_group","md_alert","cf_jt_alert_group_alert"};  

	
	private String origin_path = "/alertgroup/origin.xml";
	

	public AlertGroupServiceTest() {
		super();
		this.table = tables;
	}

	@Test  
    public void testFindByDataType() throws Exception {  
		cleanAndInsert(origin_path);
		DataType dataType = dataTypeService.findOne(1);
		List<Alert> alerts = alertService.findByDataType(dataType);
        Assert.assertEquals(3, alerts.size());
     
    } 
	
	@Test
	public void testDeleteAlertGroup() throws Exception {
		cleanAndInsert(origin_path);
		alertGroupService.deleteById(1l);
//        Assert.assertEquals(3, dataTypeService.findOne(1).getAlertGroups().size());
	}
	
	
	@Test
	public void testFindByDataTypeId() throws Exception {
		cleanAndInsert(origin_path);
		DataType dataType = new DataType();
		dataType.setId(1l);
		List<AlertGroup> alertGroups = alertGroupService.findByDataType(dataType);
        Assert.assertEquals(3, alertGroups.size());
	}

	@Override
	public void setConf() {
		// TODO Auto-generated method stub
		
	}
  
}
