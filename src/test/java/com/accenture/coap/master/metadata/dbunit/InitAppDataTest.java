package com.accenture.coap.master.metadata.dbunit;

import org.junit.Test;

public class InitAppDataTest extends DBUnitBase {
	private String origin_path = "/initdata/app-component-server.xml";

	@Test
	public void testInitData() throws Exception {
		cleanAndInsert(origin_path);
	}

	@Override
	public void setConf() {
		table = new String[] { "md_app", "md_component", "md_server" ,"md_scom_metric" };
	}
}
