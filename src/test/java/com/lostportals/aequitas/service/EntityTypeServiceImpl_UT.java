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

import com.lostportals.aequitas.db.dao.EntityTypeDao;
import com.lostportals.aequitas.db.domain.DbEntityType;
import com.lostportals.aequitas.exception.InternalServerException;
import com.lostportals.aequitas.exception.NotFoundException;
import com.lostportals.aequitas.web.db.domain.EntityType;

@RunWith(MockitoJUnitRunner.class)
public class EntityTypeServiceImpl_UT {

	@InjectMocks
	EntityTypeServiceImpl testObj;

	@Mock
	EntityTypeDao entityTypeDao;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Captor
	ArgumentCaptor<DbEntityType> dbEntityTypeCaptor;

	@Test
	public void getAll() {
		List<DbEntityType> daoList = Arrays.asList(createDbEntityType(), createDbEntityType(), createDbEntityType());
		when(entityTypeDao.getAll()).thenReturn(daoList);

		List<EntityType> actualList = testObj.getAll();

		verify(entityTypeDao).getAll();
		verifyNoMoreInteractions(entityTypeDao);
		assertNotNull(actualList);
		assertEquals(daoList.size(), actualList.size());
		for (int i = 0; i < daoList.size(); i++) {
			assertEquals(daoList.get(i).getId(), actualList.get(i).getId());
			assertEquals(daoList.get(i).getName(), actualList.get(i).getName());
			assertEquals(daoList.get(i).getParentId(), actualList.get(i).getParentId());
		}
	}

	DbEntityType createDbEntityType() {
		DbEntityType dbObj = new DbEntityType();
		dbObj.setId(UUID.randomUUID().toString());
		dbObj.setName(UUID.randomUUID().toString());
		dbObj.setParentId(UUID.randomUUID().toString());
		return dbObj;
	}

	@Test
	public void get() {
		String id = "id";
		DbEntityType dbObj = createDbEntityType();
		when(entityTypeDao.get(id)).thenReturn(dbObj);

		EntityType actualObj = testObj.get(id);

		verify(entityTypeDao).get(id);
		verifyNoMoreInteractions(entityTypeDao);
		assertNotNull(actualObj);
		assertEquals(dbObj.getId(), actualObj.getId());
		assertEquals(dbObj.getName(), actualObj.getName());
		assertEquals(dbObj.getParentId(), actualObj.getParentId());
	}

	@Test
	public void get_notFound() {
		expectedException.expect(NotFoundException.class);
		String id = "id";
		String expectedMessage = "Cannot find EntityType for id=" + id;
		expectedException.expectMessage(expectedMessage);
		when(entityTypeDao.get(id)).thenReturn(null);

		testObj.get(id);
	}

	@Test
	public void save_new_checkDaoCall() throws Exception {
		EntityType toSave = new EntityType(createDbEntityType());
		toSave.setId(null);

		testObj.save(toSave);

		verify(entityTypeDao).save(dbEntityTypeCaptor.capture());
		verifyNoMoreInteractions(entityTypeDao);
		DbEntityType capturedDbObj = dbEntityTypeCaptor.getValue();
		assertNotNull(capturedDbObj);
		assertNotEquals(toSave.getId(), capturedDbObj.getId());
		assertEquals(toSave.getName(), capturedDbObj.getName());
		assertEquals(toSave.getParentId(), capturedDbObj.getParentId());
	}

	@Test
	public void save_new_daoFail() throws Exception {
		expectedException.expect(InternalServerException.class);
		EntityType toSave = new EntityType(createDbEntityType());
		toSave.setId(null);
		expectedException.expectMessage("Unable to save entityType=" + toSave);
		when(entityTypeDao.save(any(DbEntityType.class))).thenThrow(new IllegalAccessException("something"));

		testObj.save(toSave);
	}

	@Test
	public void save_new_checkReturn() throws Exception {
		EntityType toSave = new EntityType(createDbEntityType());
		toSave.setId(null);

		EntityType actualObj = testObj.save(toSave);

		assertNotNull(actualObj);
		assertNotEquals(toSave.getId(), actualObj.getId());
		assertEquals(toSave.getName(), actualObj.getName());
		assertEquals(toSave.getParentId(), actualObj.getParentId());
	}

	@Test
	public void save_hasId_checkDaoCall() throws Exception {
		EntityType toSave = new EntityType(createDbEntityType());

		testObj.save(toSave);

		verify(entityTypeDao).save(dbEntityTypeCaptor.capture());
		verifyNoMoreInteractions(entityTypeDao);
		DbEntityType capturedDbObj = dbEntityTypeCaptor.getValue();
		assertNotNull(capturedDbObj);
		assertEquals(toSave.getId(), capturedDbObj.getId());
		assertEquals(toSave.getName(), capturedDbObj.getName());
		assertEquals(toSave.getParentId(), capturedDbObj.getParentId());
	}
}
