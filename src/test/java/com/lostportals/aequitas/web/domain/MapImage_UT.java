package com.lostportals.aequitas.web.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.web.admin.domain.Image;

@RunWith(MockitoJUnitRunner.class)
public class MapImage_UT {

	@Test
	public void constructor() {
		Image obj = new Image();
		obj.setId("id");
		obj.setEntityId("entityId");
		obj.setUrl("url");

		MapImage actualMapObj = new MapImage(obj);

		assertNotNull(actualMapObj);
		assertEquals(obj.getId(), actualMapObj.getId());
		assertEquals(obj.getEntityId(), actualMapObj.getEntityId());
		assertEquals(obj.getUrl(), actualMapObj.getUrl());
	}
}
