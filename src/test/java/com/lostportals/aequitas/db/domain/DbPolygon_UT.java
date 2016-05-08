package com.lostportals.aequitas.db.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.web.admin.domain.Polygon;

@RunWith(MockitoJUnitRunner.class)
public class DbPolygon_UT {

	@Test
	public void constructor() {
		Polygon obj = new Polygon();
		obj.setId("id");
		obj.setEntityId("entityId");
		obj.setVertices("vertices");
		obj.setOutlineColor("outlineColor");
		obj.setFillColor("fillColor");

		DbPolygon actualDbObj = new DbPolygon(obj);

		assertNotNull(actualDbObj);
		assertEquals(obj.getId(), actualDbObj.getId());
		assertEquals(obj.getEntityId(), actualDbObj.getEntityId());
		assertEquals(obj.getVertices(), actualDbObj.getVertices());
		assertEquals(obj.getOutlineColor(), actualDbObj.getOutlineColor());
		assertEquals(obj.getFillColor(), actualDbObj.getFillColor());
	}
}
