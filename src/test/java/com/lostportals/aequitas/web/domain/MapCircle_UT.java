package com.lostportals.aequitas.web.domain;

import static java.math.RoundingMode.DOWN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.web.admin.domain.Circle;

@RunWith(MockitoJUnitRunner.class)
public class MapCircle_UT {

	@Test
	public void constructor() {
		Circle obj = new Circle();
		obj.setId("id");
		obj.setEntityId("entityId");
		obj.setFillColor("fillColor");
		obj.setLatitude(new BigDecimal(Double.toString(Math.random() * 100)).setScale(8, DOWN));
		obj.setLongitude(new BigDecimal(Double.toString(Math.random() * 100)).setScale(8, DOWN));
		obj.setOutlineColor("outlineColor");
		obj.setRadius(Double.valueOf(Math.random() * 100000).intValue());

		MapCircle actualMapObj = new MapCircle(obj);

		assertNotNull(actualMapObj);
		assertEquals(obj.getId(), actualMapObj.getId());
		assertEquals(obj.getEntityId(), actualMapObj.getEntityId());
		assertEquals(obj.getFillColor(), actualMapObj.getFillColor());
		assertEquals(obj.getLatitude(), actualMapObj.getLatitude());
		assertEquals(obj.getLongitude(), actualMapObj.getLongitude());
		assertEquals(obj.getOutlineColor(), actualMapObj.getOutlineColor());
		assertEquals(obj.getRadius(), actualMapObj.getRadius());
	}
}
