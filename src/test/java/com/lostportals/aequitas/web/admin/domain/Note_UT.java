package com.lostportals.aequitas.web.admin.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.db.domain.DbNote;
import com.lostportals.aequitas.web.domain.MapNote;

@RunWith(MockitoJUnitRunner.class)
public class Note_UT {

	@Test
	public void constructor_db() {
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

	@Test
	public void constructor_map() {
		MapNote mapObj = new MapNote();
		mapObj.setId("id");
		mapObj.setEntityId("entityId");
		mapObj.setNote("note");
		mapObj.setPosition(Double.valueOf(Math.random() * 10000).intValue());

		Note actualObj = new Note(mapObj);

		assertEquals(mapObj.getId(), actualObj.getId());
		assertEquals(mapObj.getEntityId(), actualObj.getEntityId());
		assertEquals(mapObj.getNote(), actualObj.getNote());
		assertEquals(mapObj.getPosition(), actualObj.getPosition());
	}
}
