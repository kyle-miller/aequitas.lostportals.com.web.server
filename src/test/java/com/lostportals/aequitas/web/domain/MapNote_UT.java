package com.lostportals.aequitas.web.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.web.admin.domain.Note;

@RunWith(MockitoJUnitRunner.class)
public class MapNote_UT {

	@Test
	public void constructor() {
		Note obj = new Note();
		obj.setId("id");
		obj.setNote("note");
		obj.setPosition(1);

		MapNote actualMapObj = new MapNote(obj);

		assertNotNull(actualMapObj);
		assertEquals(obj.getId(), actualMapObj.getId());
		assertEquals(obj.getNote(), actualMapObj.getNote());
		assertEquals(obj.getPosition(), actualMapObj.getPosition());
	}
}
