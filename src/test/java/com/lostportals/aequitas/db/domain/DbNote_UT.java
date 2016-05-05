package com.lostportals.aequitas.db.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.web.db.domain.Note;

@RunWith(MockitoJUnitRunner.class)
public class DbNote_UT {

	@Test
	public void constructor() {
		Note obj = new Note();
		obj.setId("id");
		obj.setNote("note");
		obj.setPosition(1);

		DbNote actualDbObj = new DbNote(obj);

		assertNotNull(actualDbObj);
		assertEquals(obj.getId(), actualDbObj.getId());
		assertEquals(obj.getNote(), actualDbObj.getNote());
		assertEquals(obj.getPosition(), actualDbObj.getPosition());
	}
}
