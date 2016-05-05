package com.lostportals.aequitas.web.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.web.admin.domain.EntityType;

@RunWith(MockitoJUnitRunner.class)
public class MapEntityType_UT {

	@Test
	public void constructor() {
		EntityType obj = new EntityType();
		obj.setId("id");
		obj.setName("name");
		obj.setParentId("parentId");
		obj.setShow(true);

		MapEntityType actualMapObj = new MapEntityType(obj);

		assertNotNull(actualMapObj);
		assertEquals(obj.getId(), actualMapObj.getId());
		assertEquals(obj.getParentId(), actualMapObj.getParentId());
		assertEquals(obj.getName(), actualMapObj.getName());
		assertEquals(obj.isShow(), actualMapObj.isShow());
	}
}
