package com.ccsip.coap.master.metadata.dbunit;

import org.junit.Test;

public class InitAlertSourceTest extends DBUnitBase {
	private String origin_path = "/initdata/alertsource.xml";

	@Test
	public void testInitData() throws Exception {
		cleanAndInsert(origin_path);
	}

	@Override
	public void setConf() {
		table = new String[] { "cf_infrastructure_unit","cf_alert_source", "cf_jt_alert_source_alert_source", "cf_critical_alert_list", 
				"cf_jt_critical_alert_list_alert" };

	}
}
