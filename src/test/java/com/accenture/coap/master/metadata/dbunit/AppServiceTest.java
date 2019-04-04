package com.accenture.coap.master.metadata.dbunit;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class AppServiceTest extends DBUnitBase {
	private String origin_path = "/app/origin.xml";
	private String[] tables = new String[] { "md_app", "md_component", "md_server" };

	public AppServiceTest() {
		super();
		this.table = tables;
	}

	@Test
	public void testFindByDataType() throws Exception {
		cleanAndInsert(origin_path);

	}

	@Override
	public void setConf() {
		// TODO Auto-generated method stub
		
	}
}
