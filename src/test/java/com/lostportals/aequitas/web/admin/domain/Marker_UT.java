package com.lostportals.aequitas.web.admin.domain;

import static java.math.RoundingMode.DOWN;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.db.domain.DbMarker;
import com.lostportals.aequitas.web.admin.domain.Marker;

@RunWith(MockitoJUnitRunner.class)
public class Marker_UT {

	@Test
	public void constructor() {
		DbMarker dbObj = new DbMarker();
		dbObj.setId("id");
		dbObj.setEntityId("entityId");
		dbObj.setIconId("iconId");
		dbObj.setLatitude(new BigDecimal(Double.toString(Math.random() * 100)).setScale(8, DOWN));
		dbObj.setLongitude(new BigDecimal(Double.toString(Math.random() * 100)).setScale(8, DOWN));

		Marker actualObj = new Marker(dbObj);

		assertEquals(dbObj.getId(), actualObj.getId());
		assertEquals(dbObj.getEntityId(), actualObj.getEntityId());
		assertEquals(dbObj.getIconId(), actualObj.getIconId());
		assertEquals(dbObj.getLatitude(), actualObj.getLatitude());
		assertEquals(dbObj.getLongitude(), actualObj.getLongitude());
	}
}
