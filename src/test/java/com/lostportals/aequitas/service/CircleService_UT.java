package com.lostportals.aequitas.service;

import com.lostportals.aequitas.db.dao.CircleDao;
import com.lostportals.aequitas.db.domain.DbCircle;
import com.lostportals.aequitas.exception.InternalServerException;
import com.lostportals.aequitas.exception.NotFoundException;
import com.lostportals.aequitas.exception.UnprocessableEntityException;
import com.lostportals.aequitas.web.admin.domain.Circle;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CircleService_UT {

	@InjectMocks
	CircleService testObj;

	@Mock
	CircleDao circleDao;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Captor
	ArgumentCaptor<DbCircle> dbCircleCaptor;

	@Test
	public void getAll() {
		List<DbCircle> daoList = Arrays.asList(createDbCircle(), createDbCircle(), createDbCircle());
		when(circleDao.getAll()).thenReturn(daoList);

		List<Circle> actualList = testObj.getAll();

		verify(circleDao).getAll();
		verifyNoMoreInteractions(circleDao);
		assertNotNull(actualList);
		assertEquals(daoList.size(), actualList.size());
		for (int i = 0; i < daoList.size(); i++) {
			assertEquals(daoList.get(i).getId(), actualList.get(i).getId());
			assertEquals(daoList.get(i).getEntityId(), actualList.get(i).getEntityId());
			assertEquals(daoList.get(i).getLatitude(), new Double(actualList.get(i).getLatitude().doubleValue()));
			assertEquals(daoList.get(i).getLongitude(), new Double(actualList.get(i).getLongitude().doubleValue()));
			assertEquals(daoList.get(i).getFillColor(), actualList.get(i).getFillColor());
			assertEquals(daoList.get(i).getOutlineColor(), actualList.get(i).getOutlineColor());
			assertEquals(daoList.get(i).getRadius(), actualList.get(i).getRadius());
		}
	}

	DbCircle createDbCircle() {
		DbCircle dbObj = new DbCircle();
		dbObj.setId(UUID.randomUUID().toString());
		dbObj.setEntityId(UUID.randomUUID().toString());
		dbObj.setLatitude(Math.random() * 100);
		dbObj.setLongitude(Math.random() * 100);
		dbObj.setFillColor(UUID.randomUUID().toString());
		dbObj.setOutlineColor(UUID.randomUUID().toString());
		dbObj.setRadius(Double.valueOf(Math.random() * 10000).intValue());
		return dbObj;
	}

	@Test
	public void get() {
		String id = "id";
		DbCircle dbObj = new DbCircle();
		when(circleDao.get(id)).thenReturn(dbObj);

		Circle actualObj = testObj.get(id);

		verify(circleDao).get(id);
		verifyNoMoreInteractions(circleDao);
		assertNotNull(actualObj);
		assertEquals(dbObj.getId(), actualObj.getId());
		assertEquals(dbObj.getEntityId(), actualObj.getEntityId());
		assertEquals(dbObj.getLatitude(), actualObj.getLatitude());
		assertEquals(dbObj.getLongitude(), actualObj.getLongitude());
		assertEquals(dbObj.getFillColor(), actualObj.getFillColor());
		assertEquals(dbObj.getOutlineColor(), actualObj.getOutlineColor());
		assertEquals(dbObj.getRadius(), actualObj.getRadius());
	}

	@Test
	public void get_notFound() {
		expectedException.expect(NotFoundException.class);
		String id = "id";
		String expectedMessage = "Cannot find Circle for id=" + id;
		expectedException.expectMessage(expectedMessage);
		when(circleDao.get(id)).thenReturn(null);

		testObj.get(id);
	}

	@Test
	public void save_new_checkDaoCall() throws Exception {
		Circle toSave = new Circle(createDbCircle());
		toSave.setId(null);

		testObj.save(toSave);

		verify(circleDao).save(dbCircleCaptor.capture());
		verifyNoMoreInteractions(circleDao);
		DbCircle capturedDbObj = dbCircleCaptor.getValue();
		assertNotNull(capturedDbObj);
		assertNotEquals(toSave.getId(), capturedDbObj.getId());
		assertEquals(toSave.getEntityId(), capturedDbObj.getEntityId());
		assertEquals(new Double(toSave.getLatitude().doubleValue()), capturedDbObj.getLatitude());
		assertEquals(new Double(toSave.getLongitude().doubleValue()), capturedDbObj.getLongitude());
		assertEquals(toSave.getFillColor(), capturedDbObj.getFillColor());
		assertEquals(toSave.getOutlineColor(), capturedDbObj.getOutlineColor());
		assertEquals(toSave.getRadius(), capturedDbObj.getRadius());
	}

	@Test
	public void save_new_daoFail() throws Exception {
		expectedException.expect(InternalServerException.class);
		Circle toSave = new Circle(createDbCircle());
		expectedException.expectMessage("Unable to save circle=" + toSave);
		when(circleDao.save(any(DbCircle.class))).thenThrow(new IllegalAccessException("something"));

		testObj.save(toSave);
	}

	@Test
	public void save_new_checkReturn() throws Exception {
		Circle toSave = new Circle(createDbCircle());

		Circle actualObj = testObj.save(toSave);

		assertNotNull(actualObj);
		assertEquals(toSave.getId(), actualObj.getId());
		assertEquals(toSave.getEntityId(), actualObj.getEntityId());
		assertEquals(toSave.getLatitude(), actualObj.getLatitude());
		assertEquals(toSave.getLongitude(), actualObj.getLongitude());
		assertEquals(toSave.getFillColor(), actualObj.getFillColor());
		assertEquals(toSave.getOutlineColor(), actualObj.getOutlineColor());
		assertEquals(toSave.getRadius(), actualObj.getRadius());
	}

	@Test
	public void save_hasId_checkDaoCall() throws Exception {
		Circle toSave = new Circle(createDbCircle());

		testObj.save(toSave);

		verify(circleDao).save(dbCircleCaptor.capture());
		verifyNoMoreInteractions(circleDao);
		DbCircle capturedDbObj = dbCircleCaptor.getValue();
		assertNotNull(capturedDbObj);
		assertEquals(toSave.getId(), capturedDbObj.getId());
		assertEquals(toSave.getEntityId(), capturedDbObj.getEntityId());
		assertEquals(new Double(toSave.getLatitude().doubleValue()), capturedDbObj.getLatitude());
		assertEquals(new Double(toSave.getLongitude().doubleValue()), capturedDbObj.getLongitude());
		assertEquals(toSave.getFillColor(), capturedDbObj.getFillColor());
		assertEquals(toSave.getOutlineColor(), capturedDbObj.getOutlineColor());
		assertEquals(toSave.getRadius(), capturedDbObj.getRadius());
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
		when(circleDao.get(id)).thenReturn(new DbCircle());

		testObj.delete(id);

		verify(circleDao).delete(id);
	}
}
