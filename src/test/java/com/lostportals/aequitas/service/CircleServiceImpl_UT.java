package com.lostportals.aequitas.service;

import static java.math.RoundingMode.DOWN;
import static org.junit.Assert.assertEquals;
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

import com.lostportals.aequitas.db.dao.CircleDao;
import com.lostportals.aequitas.db.domain.DbCircle;
import com.lostportals.aequitas.exception.InternalServerException;
import com.lostportals.aequitas.exception.NotFoundException;
import com.lostportals.aequitas.web.domain.Circle;

@RunWith(MockitoJUnitRunner.class)
public class CircleServiceImpl_UT {

	@InjectMocks
	CircleServiceImpl testObj;

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
			assertEquals(daoList.get(i).getLatitude(), actualList.get(i).getLatitude());
			assertEquals(daoList.get(i).getLongitude(), actualList.get(i).getLongitude());
			assertEquals(daoList.get(i).getFillColor(), actualList.get(i).getFillColor());
			assertEquals(daoList.get(i).getOutlineColor(), actualList.get(i).getOutlineColor());
			assertEquals(daoList.get(i).getRadius(), actualList.get(i).getRadius());
		}
	}

	DbCircle createDbCircle() {
		DbCircle dbObj = new DbCircle();
		dbObj.setId(UUID.randomUUID().toString());
		dbObj.setEntityId(UUID.randomUUID().toString());
		dbObj.setLatitude(new BigDecimal(Double.toString(Math.random() * 100)).setScale(8, DOWN));
		dbObj.setLongitude(new BigDecimal(Double.toString(Math.random() * 100)).setScale(8, DOWN));
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

		testObj.save(toSave);

		verify(circleDao).save(dbCircleCaptor.capture());
		verifyNoMoreInteractions(circleDao);
		DbCircle capturedDbObj = dbCircleCaptor.getValue();
		assertNotNull(capturedDbObj);
		assertEquals(toSave.getId(), capturedDbObj.getId());
		assertEquals(toSave.getEntityId(), capturedDbObj.getEntityId());
		assertEquals(toSave.getLatitude(), capturedDbObj.getLatitude());
		assertEquals(toSave.getLongitude(), capturedDbObj.getLongitude());
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
		assertEquals(toSave.getLatitude(), capturedDbObj.getLatitude());
		assertEquals(toSave.getLongitude(), capturedDbObj.getLongitude());
		assertEquals(toSave.getFillColor(), capturedDbObj.getFillColor());
		assertEquals(toSave.getOutlineColor(), capturedDbObj.getOutlineColor());
		assertEquals(toSave.getRadius(), capturedDbObj.getRadius());
	}
}
