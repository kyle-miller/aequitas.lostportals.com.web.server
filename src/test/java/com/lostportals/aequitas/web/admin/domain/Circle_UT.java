package com.lostportals.aequitas.web.admin.domain;

import static java.math.RoundingMode.DOWN;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.db.domain.DbCircle;

@RunWith(MockitoJUnitRunner.class)
public class Circle_UT {

	@Test
	public void constructor() {
		DbCircle dbObj = new DbCircle();
		dbObj.setId("id");
		dbObj.setEntityId("entityId");
		dbObj.setFillColor("fillColor");
		dbObj.setLatitude(new BigDecimal(Double.toString(Math.random() * 100)).setScale(8, DOWN));
		dbObj.setLongitude(new BigDecimal(Double.toString(Math.random() * 100)).setScale(8, DOWN));
		dbObj.setOutlineColor("outlineColor");
		dbObj.setRadius(Double.valueOf(Math.random() * 100000).intValue());

		Circle actualObj = new Circle(dbObj);

		assertEquals(dbObj.getId(), actualObj.getId());
		assertEquals(dbObj.getEntityId(), actualObj.getEntityId());
		assertEquals(dbObj.getFillColor(), actualObj.getFillColor());
		assertEquals(dbObj.getLatitude(), actualObj.getLatitude());
		assertEquals(dbObj.getLongitude(), actualObj.getLongitude());
		assertEquals(dbObj.getOutlineColor(), actualObj.getOutlineColor());
		assertEquals(dbObj.getRadius(), actualObj.getRadius());
	}
}
