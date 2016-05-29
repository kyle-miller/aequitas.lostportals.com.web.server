package com.lostportals.aequitas.db.domain;

import static java.math.RoundingMode.DOWN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.web.admin.domain.Marker;

@RunWith(MockitoJUnitRunner.class)
public class DbMarker_UT {

	@Test
	public void constructor() {
		Marker obj = new Marker();
		obj.setId("id");
		obj.setEntityId("entityId");
		obj.setIconId("iconId");
		obj.setLatitude(new BigDecimal(Double.toString(Math.random() * 100)).setScale(8, DOWN));
		obj.setLongitude(new BigDecimal(Double.toString(Math.random() * 100)).setScale(8, DOWN));

		DbMarker actualDbObj = new DbMarker(obj);

		assertNotNull(actualDbObj);
		assertEquals(obj.getId(), actualDbObj.getId());
		assertEquals(obj.getEntityId(), actualDbObj.getEntityId());
		assertEquals(obj.getIconId(), actualDbObj.getIconId());
		assertEquals(new Double(obj.getLatitude().doubleValue()), actualDbObj.getLatitude());
		assertEquals(new Double(obj.getLongitude().doubleValue()), actualDbObj.getLongitude());
	}
}
