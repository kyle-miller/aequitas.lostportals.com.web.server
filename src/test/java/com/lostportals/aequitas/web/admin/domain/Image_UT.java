package com.lostportals.aequitas.web.admin.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.db.domain.DbImage;
import com.lostportals.aequitas.web.domain.MapImage;

@RunWith(MockitoJUnitRunner.class)
public class Image_UT {

	@Test
	public void constructor_db() {
		DbImage dbObj = new DbImage();
		dbObj.setId("id");
		dbObj.setEntityId("entityId");
		dbObj.setUrl("url");

		Image actualObj = new Image(dbObj);

		assertEquals(dbObj.getId(), actualObj.getId());
		assertEquals(dbObj.getEntityId(), actualObj.getEntityId());
		assertEquals(dbObj.getUrl(), actualObj.getUrl());
	}

	@Test
	public void constructor_map() {
		MapImage mapObj = new MapImage();
		mapObj.setId("id");
		mapObj.setEntityId("entityId");
		mapObj.setUrl("url");

		Image actualObj = new Image(mapObj);

		assertEquals(mapObj.getId(), actualObj.getId());
		assertEquals(mapObj.getEntityId(), actualObj.getEntityId());
		assertEquals(mapObj.getUrl(), actualObj.getUrl());
	}
}
