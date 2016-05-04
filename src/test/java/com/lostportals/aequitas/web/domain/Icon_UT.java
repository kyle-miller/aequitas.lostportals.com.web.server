package com.lostportals.aequitas.web.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.db.domain.DbIcon;

@RunWith(MockitoJUnitRunner.class)
public class Icon_UT {

	@Test
	public void constructor() {
		DbIcon dbObj = new DbIcon();
		dbObj.setId("id");
		dbObj.setUrl("parentId");
		dbObj.setName("name");

		Icon actualObj = new Icon(dbObj);

		assertEquals(dbObj.getId(), actualObj.getId());
		assertEquals(dbObj.getUrl(), actualObj.getUrl());
		assertEquals(dbObj.getName(), actualObj.getName());
	}
}
