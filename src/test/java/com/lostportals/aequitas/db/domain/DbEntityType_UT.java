package com.lostportals.aequitas.db.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.web.admin.domain.EntityType;

@RunWith(MockitoJUnitRunner.class)
public class DbEntityType_UT {

	@Test
	public void constructor() {
		EntityType obj = new EntityType();
		obj.setId("id");
		obj.setName("name");
		obj.setParentId("parentId");
		obj.setShow(true);

		DbEntityType actualDbObj = new DbEntityType(obj);

		assertNotNull(actualDbObj);
		assertEquals(obj.getId(), actualDbObj.getId());
		assertEquals(obj.getParentId(), actualDbObj.getParentId());
		assertEquals(obj.getName(), actualDbObj.getName());
		assertEquals(new Integer(1), actualDbObj.getShow());
	}
}
