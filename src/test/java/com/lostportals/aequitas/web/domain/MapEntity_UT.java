package com.lostportals.aequitas.web.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.web.admin.domain.Entity;

@RunWith(MockitoJUnitRunner.class)
public class MapEntity_UT {

	@Test
	public void constructor() {
		Entity obj = new Entity();
		obj.setId("id");
		obj.setTitle("title");

		MapEntity actualMapObj = new MapEntity(obj);

		assertNotNull(actualMapObj);
		assertEquals(obj.getId(), actualMapObj.getId());
		assertEquals(obj.getTitle(), actualMapObj.getTitle());
	}
}
