/*package com.ccsip.coap.master.metadata.dbunit;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccsip.coap.master.metadata.domain.confdata.AlertGroup;
import com.ccsip.coap.master.metadata.domain.confdata.DataSource;
import com.ccsip.coap.master.metadata.domain.confdata.DataType;
import com.ccsip.coap.master.metadata.domain.metadata.Alert;
import com.ccsip.coap.master.metadata.service.AlertGroupService;
import com.ccsip.coap.master.metadata.service.AlertService;
import com.ccsip.coap.master.metadata.service.DataSourceService;
import com.ccsip.coap.master.metadata.service.DataTypeService;

*//***
 *  使用dbunit进行所有多表关联时候的查询或者删除
 * @author guan.c.wang
 *
 *//*
public class CascadingTests extends DBUnitBase  {

	@Autowired 
	AlertService alertService;
	
	@Autowired
	DataTypeService dataTypeService;
	
	@Autowired 
	AlertGroupService alertGroupService;

	private  String[] tables = new String[]{"cf_data_source",
			"cf_data_type",
			"cf_alert_group",
			"md_alert",
			"cf_group_alert_mapping",
			"md_server",
			"md_server_group",
			"md_app",
			"cf_infrastructure_unit",
			"cf_critical_alerting_strategy",
			"cf_statistical_alerting_strategy",
			"cf_strategy_alert_mapping",
			"cf_alerting_object"};  

	
	private String origin_path = "/origin.xml";
	

	public CascadingTests() {
		super();
		this.table = tables;
	}

	
  
}
*/