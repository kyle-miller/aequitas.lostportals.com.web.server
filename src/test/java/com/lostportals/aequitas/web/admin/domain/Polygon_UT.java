package com.lostportals.aequitas.web.admin.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.db.domain.DbPolygon;
import com.lostportals.aequitas.web.admin.domain.Polygon;

@RunWith(MockitoJUnitRunner.class)
public class Polygon_UT {

	@Test
	public void constructor() {
		DbPolygon dbObj = new DbPolygon();
		dbObj.setId("id");
		dbObj.setEntityId("entityId");
		dbObj.setVertices("vertices");

		Polygon actualObj = new Polygon(dbObj);

		assertEquals(dbObj.getId(), actualObj.getId());
		assertEquals(dbObj.getEntityId(), actualObj.getEntityId());
		assertEquals(dbObj.getVertices(), actualObj.getVertices());
	}
}