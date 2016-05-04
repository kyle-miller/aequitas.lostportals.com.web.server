package com.lostportals.aequitas.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
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
import com.lostportals.aequitas.web.domain.EntityType;

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
		List<DbEntityType> daoList = Arrays.asList(createDbEntityType(), createDbEntityType(),
				createDbEntityType());
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

	@Test
	public void get() {
		String id = "id";
		DbEntityType dbObj = new DbEntityType();
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
		EntityType toSave = new EntityType();
		toSave.setId(null);
		toSave.setName("name");
		toSave.setParentId("parentId");
		toSave.setShow(true);
		when(entityTypeDao.save(any(DbEntityType.class))).thenReturn(true);

		testObj.save(toSave);

		verify(entityTypeDao).save(dbEntityTypeCaptor.capture());
		verifyNoMoreInteractions(entityTypeDao);
		DbEntityType capturedDbObj = dbEntityTypeCaptor.getValue();
		assertNotNull(capturedDbObj);
		assertNotEquals(toSave.getId(), capturedDbObj.getId());
		assertEquals(toSave.getParentId(), capturedDbObj.getParentId());
		assertEquals(toSave.getName(), capturedDbObj.getName());
		assertEquals(toSave.isShow(), capturedDbObj.isShow());
	}

	@Test
	public void save_new_daoFail() throws Exception {
		expectedException.expect(InternalServerException.class);
		EntityType toSave = new EntityType();
		toSave.setId(null);
		toSave.setName("name");
		toSave.setParentId("parentId");
		toSave.setShow(true);
		expectedException.expectMessage("Unable to save entityType=" + toSave);
		when(entityTypeDao.save(any(DbEntityType.class))).thenReturn(false);

		testObj.save(toSave);
	}

	@Test
	public void save_new_checkReturn() throws Exception {
		EntityType toSave = new EntityType();
		toSave.setId(null);
		toSave.setName("name");
		toSave.setParentId("parentId");
		toSave.setShow(true);
		when(entityTypeDao.save(any(DbEntityType.class))).thenReturn(true);

		EntityType actualObj = testObj.save(toSave);

		verify(entityTypeDao).save(any(DbEntityType.class));
		verifyZeroInteractions(entityTypeDao);
		assertNotNull(actualObj);
		assertNotEquals(toSave.getId(), actualObj.getId());
		assertEquals(toSave.getParentId(), actualObj.getParentId());
		assertEquals(toSave.getName(), actualObj.getName());
		assertEquals(toSave.isShow(), actualObj.isShow());
	}

	@Test
	public void save_hasId_notExists() throws Exception {
		EntityType toSave = new EntityType();
		toSave.setId("id");
		toSave.setName("name");
		toSave.setParentId("parentId");
		toSave.setShow(true);
		when(entityTypeDao.get(toSave.getId())).thenReturn(null);
		when(entityTypeDao.save(any(DbEntityType.class))).thenReturn(true);

		EntityType actualObj = testObj.save(toSave);

		verify(entityTypeDao).get(toSave.getId());
		verify(entityTypeDao).save(any(DbEntityType.class));
		verifyNoMoreInteractions(entityTypeDao);
		assertNotNull(actualObj);
		assertEquals(toSave.getId(), actualObj.getId());
		assertEquals(toSave.getParentId(), actualObj.getParentId());
		assertEquals(toSave.getName(), actualObj.getName());
		assertEquals(toSave.isShow(), actualObj.isShow());
	}

	@Test
	public void save_hasId_exists() throws Exception {
		EntityType toSave = new EntityType();
		toSave.setId("id");
		toSave.setName("name");
		toSave.setParentId("parentId");
		toSave.setShow(true);
		when(entityTypeDao.get(toSave.getId())).thenReturn(new DbEntityType(toSave));
		when(entityTypeDao.save(any(DbEntityType.class))).thenReturn(true);

		EntityType actualObj = testObj.save(toSave);

		verify(entityTypeDao).get(toSave.getId());
		verify(entityTypeDao).save(any(DbEntityType.class));
		verifyNoMoreInteractions(entityTypeDao);
		assertNotNull(actualObj);
		assertEquals(toSave.getId(), actualObj.getId());
		assertEquals(toSave.getParentId(), actualObj.getParentId());
		assertEquals(toSave.getName(), actualObj.getName());
		assertEquals(toSave.isShow(), actualObj.isShow());
	}

	DbEntityType createDbEntityType() {
		DbEntityType dbObj = new DbEntityType();
		dbObj.setId(UUID.randomUUID().toString());
		dbObj.setName(UUID.randomUUID().toString());
		dbObj.setParentId(UUID.randomUUID().toString());
		return dbObj;
	}
}
