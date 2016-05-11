package com.wonders.bigdata.manageplatform.service.metadatatable;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wonders.bigdata.manageplatform.service.metadata.service.MetadataTableService;
import com.wonders.bud.framework.common.AbstractTestCase;


public class MetadataTableServiceTest  extends AbstractTestCase{

	@Autowired
	private MetadataTableService metadataTableService;
	
	@Test
	public void ready() {
	Assert.assertNotNull(metadataTableService);
	}
	
	@Test
	public void getMetaTablesTest() {
		//metadataTableService.getMetaTables("1", 11111L);
	}
	
	@Test
	public void getAllRejectedMetadataTabel(){
		Assert.assertEquals(1, metadataTableService.getAllRejectedMetadataTabel().size());
	}
}
