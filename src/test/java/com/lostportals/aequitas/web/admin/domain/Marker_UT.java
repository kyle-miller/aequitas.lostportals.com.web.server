package com.lostportals.aequitas.web.admin.domain;

import static java.math.RoundingMode.DOWN;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.db.domain.DbMarker;
import com.lostportals.aequitas.web.domain.MapMarker;

@RunWith(MockitoJUnitRunner.class)
public class Marker_UT {

	@Test
	public void constructor_db() {
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

	@Test
	public void constructor_map() {
		MapMarker mapObj = new MapMarker();
		mapObj.setId("id");
		mapObj.setEntityId("entityId");
		mapObj.setIconId("iconId");
		mapObj.setLatitude(new BigDecimal(Double.toString(Math.random() * 100)).setScale(8, DOWN));
		mapObj.setLongitude(new BigDecimal(Double.toString(Math.random() * 100)).setScale(8, DOWN));

		Marker actualObj = new Marker(mapObj);

		assertEquals(mapObj.getId(), actualObj.getId());
		assertEquals(mapObj.getEntityId(), actualObj.getEntityId());
		assertEquals(mapObj.getIconId(), actualObj.getIconId());
		assertEquals(mapObj.getLatitude(), actualObj.getLatitude());
		assertEquals(mapObj.getLongitude(), actualObj.getLongitude());
	}
}
