package com.lostportals.aequitas.web.admin.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.db.domain.DbEntityType;
import com.lostportals.aequitas.web.admin.domain.EntityType;

@RunWith(MockitoJUnitRunner.class)
public class EntityType_UT {

	@Test
	public void constructor() {
		DbEntityType dbObj = new DbEntityType();
		dbObj.setId("id");
		dbObj.setParentId("parentId");
		dbObj.setName("name");
		dbObj.setShow(true);

		EntityType actualObj = new EntityType(dbObj);

		assertEquals(dbObj.getId(), actualObj.getId());
		assertEquals(dbObj.getParentId(), actualObj.getParentId());
		assertEquals(dbObj.getName(), actualObj.getName());
		assertEquals(dbObj.isShow(), actualObj.isShow());
	}
}
