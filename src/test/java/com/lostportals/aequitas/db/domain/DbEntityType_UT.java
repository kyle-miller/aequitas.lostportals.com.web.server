package com.lostportals.aequitas.db.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.web.domain.EntityType;

@RunWith(MockitoJUnitRunner.class)
public class DbEntityType_UT {

	@Test
	public void constructor() {
		EntityType expectedEntityType = new EntityType();
		expectedEntityType.setId("id");
		expectedEntityType.setName("name");
		expectedEntityType.setParentId("parentId");
		expectedEntityType.setShow(true);

		DbEntityType actualDbEntityType = new DbEntityType(expectedEntityType);

		assertNotNull(actualDbEntityType);
		assertEquals(expectedEntityType.getId(), actualDbEntityType.getId());
		assertEquals(expectedEntityType.getParentId(), actualDbEntityType.getParentId());
		assertEquals(expectedEntityType.getName(), actualDbEntityType.getName());
		assertEquals(expectedEntityType.isShow(), actualDbEntityType.isShow());
	}
}
