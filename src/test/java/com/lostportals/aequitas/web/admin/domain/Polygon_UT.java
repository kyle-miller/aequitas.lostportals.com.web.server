package com.lostportals.aequitas.web.admin.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.db.domain.DbPolygon;

@RunWith(MockitoJUnitRunner.class)
public class Polygon_UT {

	@Test
	public void constructor() {
		DbPolygon dbObj = new DbPolygon();
		dbObj.setId("id");
		dbObj.setEntityId("entityId");
		dbObj.setVertices("vertices");
		dbObj.setOutlineColor("outlineColor");
		dbObj.setFillColor("fillColor");

		Polygon actualObj = new Polygon(dbObj);

		assertEquals(dbObj.getId(), actualObj.getId());
		assertEquals(dbObj.getEntityId(), actualObj.getEntityId());
		assertEquals(dbObj.getVertices(), actualObj.getVertices());
		assertEquals(dbObj.getOutlineColor(), actualObj.getOutlineColor());
		assertEquals(dbObj.getFillColor(), actualObj.getFillColor());
	}
}