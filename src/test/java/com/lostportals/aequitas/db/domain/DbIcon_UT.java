package com.lostportals.aequitas.db.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.web.db.domain.Icon;

@RunWith(MockitoJUnitRunner.class)
public class DbIcon_UT {

	@Test
	public void constructor() {
		Icon obj = new Icon();
		obj.setId("id");
		obj.setName("name");
		obj.setUrl("url");

		DbIcon actualDbObj = new DbIcon(obj);

		assertNotNull(actualDbObj);
		assertEquals(obj.getId(), actualDbObj.getId());
		assertEquals(obj.getUrl(), actualDbObj.getUrl());
		assertEquals(obj.getName(), actualDbObj.getName());
	}
}
