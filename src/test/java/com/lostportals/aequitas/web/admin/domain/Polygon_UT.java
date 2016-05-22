package com.lostportals.aequitas.web.admin.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.db.domain.DbPolygon;
import com.lostportals.aequitas.web.domain.MapPolygon;

@RunWith(MockitoJUnitRunner.class)
public class Polygon_UT {

	@Test
	public void constructor_db() {
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

	@Test
	public void constructor_map() {
		MapPolygon mapObj = new MapPolygon();
		mapObj.setId("id");
		mapObj.setEntityId("entityId");
		mapObj.setVertices("vertices");
		mapObj.setOutlineColor("outlineColor");
		mapObj.setFillColor("fillColor");

		Polygon actualObj = new Polygon(mapObj);

		assertEquals(mapObj.getId(), actualObj.getId());
		assertEquals(mapObj.getEntityId(), actualObj.getEntityId());
		assertEquals(mapObj.getVertices(), actualObj.getVertices());
		assertEquals(mapObj.getOutlineColor(), actualObj.getOutlineColor());
		assertEquals(mapObj.getFillColor(), actualObj.getFillColor());
	}
}