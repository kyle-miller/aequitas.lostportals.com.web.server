package com.lostportals.aequitas.service;

import static java.math.RoundingMode.DOWN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.db.dao.MarkerDao;
import com.lostportals.aequitas.db.domain.DbMarker;
import com.lostportals.aequitas.exception.InternalServerException;
import com.lostportals.aequitas.exception.NotFoundException;
import com.lostportals.aequitas.web.db.domain.Marker;

@RunWith(MockitoJUnitRunner.class)
public class MarkerServiceImpl_UT {

	@InjectMocks
	MarkerServiceImpl testObj;

	@Mock
	MarkerDao markerDao;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Captor
	ArgumentCaptor<DbMarker> dbMarkerCaptor;

	@Test
	public void getAll() {
		List<DbMarker> daoList = Arrays.asList(createDbMarker(), createDbMarker(), createDbMarker());
		when(markerDao.getAll()).thenReturn(daoList);

		List<Marker> actualList = testObj.getAll();

		verify(markerDao).getAll();
		verifyNoMoreInteractions(markerDao);
		assertNotNull(actualList);
		assertEquals(daoList.size(), actualList.size());
		for (int i = 0; i < daoList.size(); i++) {
			assertEquals(daoList.get(i).getId(), actualList.get(i).getId());
			assertEquals(daoList.get(i).getEntityId(), actualList.get(i).getEntityId());
			assertEquals(daoList.get(i).getIconId(), actualList.get(i).getIconId());
			assertEquals(daoList.get(i).getLatitude(), actualList.get(i).getLatitude());
			assertEquals(daoList.get(i).getLongitude(), actualList.get(i).getLongitude());
		}
	}

	DbMarker createDbMarker() {
		DbMarker dbObj = new DbMarker();
		dbObj.setId(UUID.randomUUID().toString());
		dbObj.setEntityId(UUID.randomUUID().toString());
		dbObj.setIconId(UUID.randomUUID().toString());
		dbObj.setLatitude(new BigDecimal(Double.toString(Math.random() * 100)).setScale(8, DOWN));
		dbObj.setLongitude(new BigDecimal(Double.toString(Math.random() * 100)).setScale(8, DOWN));
		return dbObj;
	}

	@Test
	public void get() {
		String id = "id";
		DbMarker dbObj = createDbMarker();
		when(markerDao.get(id)).thenReturn(dbObj);

		Marker actualObj = testObj.get(id);

		verify(markerDao).get(id);
		verifyNoMoreInteractions(markerDao);
		assertNotNull(actualObj);
		assertEquals(dbObj.getId(), actualObj.getId());
		assertEquals(dbObj.getEntityId(), actualObj.getEntityId());
		assertEquals(dbObj.getIconId(), actualObj.getIconId());
		assertEquals(dbObj.getLatitude(), actualObj.getLatitude());
		assertEquals(dbObj.getLongitude(), actualObj.getLongitude());
	}

	@Test
	public void get_notFound() {
		expectedException.expect(NotFoundException.class);
		String id = "id";
		String expectedMessage = "Cannot find Marker for id=" + id;
		expectedException.expectMessage(expectedMessage);
		when(markerDao.get(id)).thenReturn(null);

		testObj.get(id);
	}

	@Test
	public void save_new_checkDaoCall() throws Exception {
		Marker toSave = new Marker(createDbMarker());
		toSave.setId(null);

		testObj.save(toSave);

		verify(markerDao).save(dbMarkerCaptor.capture());
		verifyNoMoreInteractions(markerDao);
		DbMarker capturedDbObj = dbMarkerCaptor.getValue();
		assertNotNull(capturedDbObj);
		assertNotEquals(toSave.getId(), capturedDbObj.getId());
		assertEquals(toSave.getEntityId(), capturedDbObj.getEntityId());
		assertEquals(toSave.getIconId(), capturedDbObj.getIconId());
		assertEquals(toSave.getLatitude(), capturedDbObj.getLatitude());
		assertEquals(toSave.getLongitude(), capturedDbObj.getLongitude());
	}

	@Test
	public void save_new_daoFail() throws Exception {
		expectedException.expect(InternalServerException.class);
		Marker toSave = new Marker(createDbMarker());
		toSave.setId(null);
		expectedException.expectMessage("Unable to save marker=" + toSave);
		when(markerDao.save(any(DbMarker.class))).thenThrow(new IllegalAccessException("something"));

		testObj.save(toSave);
	}

	@Test
	public void save_new_checkReturn() throws Exception {
		Marker toSave = new Marker(createDbMarker());
		toSave.setId(null);

		Marker actualObj = testObj.save(toSave);

		assertNotNull(actualObj);
		assertNotEquals(toSave.getId(), actualObj.getId());
		assertEquals(toSave.getEntityId(), actualObj.getEntityId());
		assertEquals(toSave.getIconId(), actualObj.getIconId());
		assertEquals(toSave.getLatitude(), actualObj.getLatitude());
		assertEquals(toSave.getLongitude(), actualObj.getLongitude());
	}

	@Test
	public void save_hasId_checkDaoCall() throws Exception {
		Marker toSave = new Marker(createDbMarker());

		testObj.save(toSave);

		verify(markerDao).save(dbMarkerCaptor.capture());
		verifyNoMoreInteractions(markerDao);
		DbMarker capturedDbObj = dbMarkerCaptor.getValue();
		assertNotNull(capturedDbObj);
		assertEquals(toSave.getId(), capturedDbObj.getId());
		assertEquals(toSave.getEntityId(), capturedDbObj.getEntityId());
		assertEquals(toSave.getIconId(), capturedDbObj.getIconId());
		assertEquals(toSave.getLatitude(), capturedDbObj.getLatitude());
		assertEquals(toSave.getLongitude(), capturedDbObj.getLongitude());
	}
}
