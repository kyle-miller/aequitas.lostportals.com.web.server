package com.lostportals.aequitas.web.admin.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.db.domain.DbNote;

@RunWith(MockitoJUnitRunner.class)
public class Note_UT {

	@Test
	public void constructor() {
		DbNote dbObj = new DbNote();
		dbObj.setId("id");
		dbObj.setEntityId("entityId");
		dbObj.setNote("note");
		dbObj.setPosition(Double.valueOf(Math.random() * 10000).intValue());

		Note actualObj = new Note(dbObj);

		assertEquals(dbObj.getId(), actualObj.getId());
		assertEquals(dbObj.getEntityId(), actualObj.getEntityId());
		assertEquals(dbObj.getNote(), actualObj.getNote());
		assertEquals(dbObj.getPosition(), actualObj.getPosition());
	}
}
