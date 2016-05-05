package com.lostportals.aequitas.web.domain;

import static java.math.RoundingMode.DOWN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.web.admin.domain.Marker;

@RunWith(MockitoJUnitRunner.class)
public class MapMarker_UT {

	@Test
	public void constructor() {
		Marker obj = new Marker();
		obj.setId("id");
		obj.setEntityId("entityId");
		obj.setIconId("iconId");
		obj.setLatitude(new BigDecimal(Double.toString(Math.random() * 100)).setScale(8, DOWN));
		obj.setLongitude(new BigDecimal(Double.toString(Math.random() * 100)).setScale(8, DOWN));

		MapMarker actualMapObj = new MapMarker(obj);

		assertNotNull(actualMapObj);
		assertEquals(obj.getId(), actualMapObj.getId());
		assertEquals(obj.getEntityId(), actualMapObj.getEntityId());
		assertEquals(obj.getIconId(), actualMapObj.getIconId());
		assertEquals(obj.getLatitude(), actualMapObj.getLatitude());
		assertEquals(obj.getLongitude(), actualMapObj.getLongitude());
	}
}
