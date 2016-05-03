package com.lostportals.aequitas.web.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.db.domain.DbEntityType;

@RunWith(MockitoJUnitRunner.class)
public class EntityType_UT {

	@Test
	public void constructor() {
		DbEntityType dbEntityType = new DbEntityType();
		dbEntityType.setId("id");
		dbEntityType.setParentId("parentId");
		dbEntityType.setName("name");
		dbEntityType.setShow(true);

		EntityType actualEntityType = new EntityType(dbEntityType);

		assertEquals(dbEntityType.getId(), actualEntityType.getId());
		assertEquals(dbEntityType.getParentId(), actualEntityType.getParentId());
		assertEquals(dbEntityType.getName(), actualEntityType.getName());
		assertEquals(dbEntityType.isShow(), actualEntityType.isShow());
	}
}
