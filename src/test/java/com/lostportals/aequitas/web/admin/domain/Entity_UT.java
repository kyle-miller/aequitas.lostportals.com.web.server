package com.lostportals.aequitas.web.admin.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.db.domain.DbEntity;
import com.lostportals.aequitas.web.domain.MapEntity;

@RunWith(MockitoJUnitRunner.class)
public class Entity_UT {

	@Test
	public void constructor_db() {
		DbEntity dbObj = new DbEntity();
		dbObj.setId("id");
		dbObj.setTitle("title");

		Entity actualObj = new Entity(dbObj);

		assertEquals(dbObj.getId(), actualObj.getId());
		assertEquals(dbObj.getTitle(), actualObj.getTitle());
	}

	@Test
	public void constructor_map() {
		MapEntity mapObj = new MapEntity();
		mapObj.setId("id");
		mapObj.setTitle("title");

		Entity actualObj = new Entity(mapObj);

		assertEquals(mapObj.getId(), actualObj.getId());
		assertEquals(mapObj.getTitle(), actualObj.getTitle());
	}
}
