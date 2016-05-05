package com.lostportals.aequitas.db.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.web.db.domain.Entity;

@RunWith(MockitoJUnitRunner.class)
public class DbEntity_UT {

	@Test
	public void constructor() {
		Entity obj = new Entity();
		obj.setId("id");
		obj.setTitle("title");

		DbEntity actualDbObj = new DbEntity(obj);

		assertNotNull(actualDbObj);
		assertEquals(obj.getId(), actualDbObj.getId());
		assertEquals(obj.getTitle(), actualDbObj.getTitle());
	}
}
