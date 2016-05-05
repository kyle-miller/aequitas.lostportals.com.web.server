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

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.db.dao.PolygonDao;
import com.lostportals.aequitas.db.domain.DbPolygon;
import com.lostportals.aequitas.exception.InternalServerException;
import com.lostportals.aequitas.exception.NotFoundException;
import com.lostportals.aequitas.web.domain.Polygon;

@RunWith(MockitoJUnitRunner.class)
public class PolygonServiceImpl_UT {

	@InjectMocks
	PolygonServiceImpl testObj;

	@Mock
	PolygonDao polygonDao;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Captor
	ArgumentCaptor<DbPolygon> dbPolygonCaptor;

	@Test
	public void getAll() {
		List<DbPolygon> daoList = Arrays.asList(createDbPolygon(), createDbPolygon(), createDbPolygon());
		when(polygonDao.getAll()).thenReturn(daoList);

		List<Polygon> actualList = testObj.getAll();

		verify(polygonDao).getAll();
		verifyNoMoreInteractions(polygonDao);
		assertNotNull(actualList);
		assertEquals(daoList.size(), actualList.size());
		for (int i = 0; i < daoList.size(); i++) {
			assertEquals(daoList.get(i).getId(), actualList.get(i).getId());
			assertEquals(daoList.get(i).getEntityId(), actualList.get(i).getEntityId());
			assertEquals(daoList.get(i).getVertices(), actualList.get(i).getVertices());
		}
	}

	DbPolygon createDbPolygon() {
		DbPolygon dbObj = new DbPolygon();
		dbObj.setId(UUID.randomUUID().toString());
		dbObj.setEntityId(UUID.randomUUID().toString());
		dbObj.setVertices(UUID.randomUUID().toString());
		return dbObj;
	}

	@Test
	public void get() {
		String id = "id";
		DbPolygon dbObj = createDbPolygon();
		when(polygonDao.get(id)).thenReturn(dbObj);

		Polygon actualObj = testObj.get(id);

		verify(polygonDao).get(id);
		verifyNoMoreInteractions(polygonDao);
		assertNotNull(actualObj);
		assertEquals(dbObj.getId(), actualObj.getId());
		assertEquals(dbObj.getEntityId(), actualObj.getEntityId());
		assertEquals(dbObj.getVertices(), actualObj.getVertices());
	}

	@Test
	public void get_notFound() {
		expectedException.expect(NotFoundException.class);
		String id = "id";
		String expectedMessage = "Cannot find Polygon for id=" + id;
		expectedException.expectMessage(expectedMessage);
		when(polygonDao.get(id)).thenReturn(null);

		testObj.get(id);
	}

	@Test
	public void save_new_checkDaoCall() throws Exception {
		Polygon toSave = new Polygon(createDbPolygon());
		toSave.setId(null);

		testObj.save(toSave);

		verify(polygonDao).save(dbPolygonCaptor.capture());
		verifyNoMoreInteractions(polygonDao);
		DbPolygon capturedDbObj = dbPolygonCaptor.getValue();
		assertNotNull(capturedDbObj);
		assertNotEquals(toSave.getId(), capturedDbObj.getId());
		assertEquals(toSave.getEntityId(), capturedDbObj.getEntityId());
		assertEquals(toSave.getVertices(), capturedDbObj.getVertices());
	}

	@Test
	public void save_new_daoFail() throws Exception {
		expectedException.expect(InternalServerException.class);
		Polygon toSave = new Polygon(createDbPolygon());
		toSave.setId(null);
		expectedException.expectMessage("Unable to save polygon=" + toSave);
		when(polygonDao.save(any(DbPolygon.class))).thenThrow(new IllegalAccessException("something"));

		testObj.save(toSave);
	}

	@Test
	public void save_new_checkReturn() throws Exception {
		Polygon toSave = new Polygon(createDbPolygon());
		toSave.setId(null);

		Polygon actualObj = testObj.save(toSave);

		assertNotNull(actualObj);
		assertNotEquals(toSave.getId(), actualObj.getId());
		assertEquals(toSave.getEntityId(), actualObj.getEntityId());
		assertEquals(toSave.getVertices(), actualObj.getVertices());
	}

	@Test
	public void save_hasId_checkDaoCall() throws Exception {
		Polygon toSave = new Polygon(createDbPolygon());

		testObj.save(toSave);

		verify(polygonDao).save(dbPolygonCaptor.capture());
		verifyNoMoreInteractions(polygonDao);
		DbPolygon capturedDbObj = dbPolygonCaptor.getValue();
		assertNotNull(capturedDbObj);
		assertEquals(toSave.getId(), capturedDbObj.getId());
		assertEquals(toSave.getEntityId(), capturedDbObj.getEntityId());
		assertEquals(toSave.getVertices(), capturedDbObj.getVertices());
	}
}
