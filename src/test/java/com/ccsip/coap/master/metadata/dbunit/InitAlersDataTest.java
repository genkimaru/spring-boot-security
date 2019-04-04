package com.ccsip.coap.master.metadata.dbunit;

import org.junit.Test;

public class InitAlersDataTest extends DBUnitBase {
	private String origin_path = "/initdata/alert-alertgroup.xml";

	@Test
	public void testInitData() throws Exception {
		cleanAndInsert(origin_path);
	}

	@Override
	public void setConf() {
		table = new String[] { "cf_data_source", "cf_data_type", "cf_alert_group", "md_alert",
				"cf_jt_alert_group_alert" };

	}
}
