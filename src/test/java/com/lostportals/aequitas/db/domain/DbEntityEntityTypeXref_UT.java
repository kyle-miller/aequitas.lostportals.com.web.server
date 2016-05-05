package com.lostportals.aequitas.db.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.web.admin.domain.EntityEntityTypeXref;

@RunWith(MockitoJUnitRunner.class)
public class DbEntityEntityTypeXref_UT {

	@Test
	public void constructor() {
		EntityEntityTypeXref obj = new EntityEntityTypeXref();
		obj.setId("id");
		obj.setEntityId("entityId");
		obj.setEntityTypeId("entityTypeId");

		DbEntityEntityTypeXref actualDbObj = new DbEntityEntityTypeXref(obj);

		assertNotNull(actualDbObj);
		assertEquals(obj.getId(), actualDbObj.getId());
		assertEquals(obj.getEntityId(), actualDbObj.getEntityId());
		assertEquals(obj.getEntityTypeId(), actualDbObj.getEntityTypeId());
	}
}
