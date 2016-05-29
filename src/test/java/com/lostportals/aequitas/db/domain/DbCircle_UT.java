package com.lostportals.aequitas.db.domain;

import static java.math.RoundingMode.DOWN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.web.admin.domain.Circle;

@RunWith(MockitoJUnitRunner.class)
public class DbCircle_UT {

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

		DbCircle actualDbObj = new DbCircle(obj);

		assertNotNull(actualDbObj);
		assertEquals(obj.getId(), actualDbObj.getId());
		assertEquals(obj.getEntityId(), actualDbObj.getEntityId());
		assertEquals(obj.getFillColor(), actualDbObj.getFillColor());
		assertEquals(new Double(obj.getLatitude().doubleValue()), actualDbObj.getLatitude());
		assertEquals(new Double(obj.getLongitude().doubleValue()), actualDbObj.getLongitude());
		assertEquals(obj.getOutlineColor(), actualDbObj.getOutlineColor());
		assertEquals(obj.getRadius(), actualDbObj.getRadius());
	}
}
