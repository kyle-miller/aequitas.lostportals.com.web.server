package com.lostportals.aequitas.web.db.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.db.domain.DbEntityEntityTypeXref;

@RunWith(MockitoJUnitRunner.class)
public class EntityEntityTypeXref_UT {

	@Test
	public void constructor() {
		DbEntityEntityTypeXref dbObj = new DbEntityEntityTypeXref();
		dbObj.setId("id");
		dbObj.setEntityId("entityId");
		dbObj.setEntityTypeId("entityTypeId");

		EntityEntityTypeXref actualObj = new EntityEntityTypeXref(dbObj);

		assertEquals(dbObj.getId(), actualObj.getId());
		assertEquals(dbObj.getEntityId(), actualObj.getEntityId());
		assertEquals(dbObj.getEntityTypeId(), actualObj.getEntityTypeId());
	}
}
