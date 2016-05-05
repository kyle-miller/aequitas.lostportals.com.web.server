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

import com.lostportals.aequitas.db.dao.EntityDao;
import com.lostportals.aequitas.db.domain.DbEntity;
import com.lostportals.aequitas.exception.InternalServerException;
import com.lostportals.aequitas.exception.NotFoundException;
import com.lostportals.aequitas.web.domain.Entity;

@RunWith(MockitoJUnitRunner.class)
public class EntityServiceImpl_UT {

	@InjectMocks
	EntityServiceImpl testObj;

	@Mock
	EntityDao entityDao;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Captor
	ArgumentCaptor<DbEntity> dbEntityCaptor;

	@Test
	public void getAll() {
		List<DbEntity> daoList = Arrays.asList(createDbEntity(), createDbEntity(), createDbEntity());
		when(entityDao.getAll()).thenReturn(daoList);

		List<Entity> actualList = testObj.getAll();

		verify(entityDao).getAll();
		verifyNoMoreInteractions(entityDao);
		assertNotNull(actualList);
		assertEquals(daoList.size(), actualList.size());
		for (int i = 0; i < daoList.size(); i++) {
			assertEquals(daoList.get(i).getId(), actualList.get(i).getId());
			assertEquals(daoList.get(i).getTitle(), actualList.get(i).getTitle());
		}
	}

	DbEntity createDbEntity() {
		DbEntity dbObj = new DbEntity();
		dbObj.setId(UUID.randomUUID().toString());
		dbObj.setTitle(UUID.randomUUID().toString());
		return dbObj;
	}

	@Test
	public void get() {
		String id = "id";
		DbEntity dbObj = createDbEntity();
		when(entityDao.get(id)).thenReturn(dbObj);

		Entity actualObj = testObj.get(id);

		verify(entityDao).get(id);
		verifyNoMoreInteractions(entityDao);
		assertNotNull(actualObj);
		assertEquals(dbObj.getId(), actualObj.getId());
		assertEquals(dbObj.getTitle(), actualObj.getTitle());
	}

	@Test
	public void get_notFound() {
		expectedException.expect(NotFoundException.class);
		String id = "id";
		String expectedMessage = "Cannot find Entity for id=" + id;
		expectedException.expectMessage(expectedMessage);
		when(entityDao.get(id)).thenReturn(null);

		testObj.get(id);
	}

	@Test
	public void save_new_checkDaoCall() throws Exception {
		Entity toSave = new Entity(createDbEntity());
		toSave.setId(null);

		testObj.save(toSave);

		verify(entityDao).save(dbEntityCaptor.capture());
		verifyNoMoreInteractions(entityDao);
		DbEntity capturedDbObj = dbEntityCaptor.getValue();
		assertNotNull(capturedDbObj);
		assertNotEquals(toSave.getId(), capturedDbObj.getId());
		assertEquals(toSave.getTitle(), capturedDbObj.getTitle());
	}

	@Test
	public void save_new_daoFail() throws Exception {
		expectedException.expect(InternalServerException.class);
		Entity toSave = new Entity(createDbEntity());
		toSave.setId(null);
		expectedException.expectMessage("Unable to save entity=" + toSave);
		when(entityDao.save(any(DbEntity.class))).thenThrow(new IllegalAccessException("something"));

		testObj.save(toSave);
	}

	@Test
	public void save_new_checkReturn() throws Exception {
		Entity toSave = new Entity(createDbEntity());
		toSave.setId(null);

		Entity actualObj = testObj.save(toSave);

		assertNotNull(actualObj);
		assertNotEquals(toSave.getId(), actualObj.getId());
		assertEquals(toSave.getTitle(), actualObj.getTitle());
	}

	@Test
	public void save_hasId_checkDaoCall() throws Exception {
		Entity toSave = new Entity(createDbEntity());

		testObj.save(toSave);

		verify(entityDao).save(dbEntityCaptor.capture());
		verifyNoMoreInteractions(entityDao);
		DbEntity capturedDbObj = dbEntityCaptor.getValue();
		assertNotNull(capturedDbObj);
		assertEquals(toSave.getId(), capturedDbObj.getId());
		assertEquals(toSave.getTitle(), capturedDbObj.getTitle());
	}
}
