package com.lostportals.aequitas.db.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.web.admin.domain.Image;

@RunWith(MockitoJUnitRunner.class)
public class DbImage_UT {

	@Test
	public void constructor() {
		Image obj = new Image();
		obj.setId("id");
		obj.setEntityId("entityId");
		obj.setUrl("image");

		DbImage actualDbObj = new DbImage(obj);

		assertNotNull(actualDbObj);
		assertEquals(obj.getId(), actualDbObj.getId());
		assertEquals(obj.getEntityId(), actualDbObj.getEntityId());
		assertEquals(obj.getUrl(), actualDbObj.getUrl());
	}
}
