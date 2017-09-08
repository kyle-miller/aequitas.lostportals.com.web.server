package com.lostportals.aequitas.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.lostportals.aequitas.db.domain.DbNote;
import com.lostportals.aequitas.exception.UnprocessableEntityException;
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
import com.lostportals.aequitas.web.admin.domain.Marker;

@RunWith(MockitoJUnitRunner.class)
public class MarkerService_UT {

	@InjectMocks
	MarkerService testObj;

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
			assertEquals(daoList.get(i).getLatitude(), new Double(actualList.get(i).getLatitude().doubleValue()));
			assertEquals(daoList.get(i).getLongitude(), new Double(actualList.get(i).getLongitude().doubleValue()));
		}
	}

	DbMarker createDbMarker() {
		DbMarker dbObj = new DbMarker();
		dbObj.setId(UUID.randomUUID().toString());
		dbObj.setEntityId(UUID.randomUUID().toString());
		dbObj.setIconId(UUID.randomUUID().toString());
		dbObj.setLatitude(Math.random() * 100);
		dbObj.setLongitude(Math.random() * 100);
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
		assertEquals(dbObj.getLatitude(), new Double(actualObj.getLatitude().doubleValue()));
		assertEquals(dbObj.getLongitude(), new Double(actualObj.getLongitude().doubleValue()));
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
		assertEquals(new Double(toSave.getLatitude().doubleValue()), capturedDbObj.getLatitude());
		assertEquals(new Double(toSave.getLongitude().doubleValue()), capturedDbObj.getLongitude());
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
		assertEquals(new Double(toSave.getLatitude().doubleValue()), capturedDbObj.getLatitude());
		assertEquals(new Double(toSave.getLongitude().doubleValue()), capturedDbObj.getLongitude());
	}

	@Test
	public void delete_noId() {
		expectedException.expect(UnprocessableEntityException.class);
		expectedException.expectMessage("id is required");

		testObj.delete(null);
	}

	@Test
	public void delete_success() {
		String id = "objectId";
		when(markerDao.get(id)).thenReturn(new DbMarker());

		testObj.delete(id);

		verify(markerDao).delete(id);
	}
}
