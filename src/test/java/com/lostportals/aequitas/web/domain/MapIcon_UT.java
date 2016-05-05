package com.lostportals.aequitas.web.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.web.admin.domain.Icon;

@RunWith(MockitoJUnitRunner.class)
public class MapIcon_UT {

	@Test
	public void constructor() {
		Icon obj = new Icon();
		obj.setId("id");
		obj.setName("name");
		obj.setUrl("url");

		MapIcon actualMapObj = new MapIcon(obj);

		assertNotNull(actualMapObj);
		assertEquals(obj.getId(), actualMapObj.getId());
		assertEquals(obj.getUrl(), actualMapObj.getUrl());
		assertEquals(obj.getName(), actualMapObj.getName());
	}
}
