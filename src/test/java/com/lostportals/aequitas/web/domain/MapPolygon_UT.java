package com.lostportals.aequitas.web.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.web.admin.domain.Polygon;

@RunWith(MockitoJUnitRunner.class)
public class MapPolygon_UT {

	@Test
	public void constructor() {
		Polygon obj = new Polygon();
		obj.setId("id");
		obj.setEntityId("entityId");
		obj.setVertices("vertices");

		MapPolygon actualMapObj = new MapPolygon(obj);

		assertNotNull(actualMapObj);
		assertEquals(obj.getId(), actualMapObj.getId());
		assertEquals(obj.getEntityId(), actualMapObj.getEntityId());
		assertEquals(obj.getVertices(), actualMapObj.getVertices());
	}
}
