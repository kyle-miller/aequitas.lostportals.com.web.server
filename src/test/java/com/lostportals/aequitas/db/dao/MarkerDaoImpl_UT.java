package com.lostportals.aequitas.db.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MarkerDaoImpl_UT {
	@InjectMocks
	MarkerDaoImpl testObj;

	@Test
	public void getRowMapper_notNull() {
		assertNotNull(testObj.getRowMapper());
	}

	@Test
	public void getTableName() {
		assertEquals("markers", testObj.getTableName());
	}
}
