package com.lostportals.aequitas.web.db.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.db.domain.DbEntity;

@RunWith(MockitoJUnitRunner.class)
public class Entity_UT {

	@Test
	public void constructor() {
		DbEntity dbObj = new DbEntity();
		dbObj.setId("id");
		dbObj.setTitle("title");

		Entity actualObj = new Entity(dbObj);

		assertEquals(dbObj.getId(), actualObj.getId());
		assertEquals(dbObj.getTitle(), actualObj.getTitle());
	}
}
