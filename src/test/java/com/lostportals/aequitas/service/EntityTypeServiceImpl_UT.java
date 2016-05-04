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
		List<DbEntityType> daoResultList = Arrays.asList(createDbEntityType(), createDbEntityType(),
				createDbEntityType());
		when(entityTypeDao.getAll()).thenReturn(daoResultList);

		List<EntityType> actualEntityTypeList = testObj.getAll();

		verify(entityTypeDao).getAll();
		verifyNoMoreInteractions(entityTypeDao);
		assertNotNull(actualEntityTypeList);
		assertEquals(daoResultList.size(), actualEntityTypeList.size());
		for (int i = 0; i < daoResultList.size(); i++) {
			assertEquals(daoResultList.get(i).getId(), actualEntityTypeList.get(i).getId());
			assertEquals(daoResultList.get(i).getName(), actualEntityTypeList.get(i).getName());
			assertEquals(daoResultList.get(i).getParentId(), actualEntityTypeList.get(i).getParentId());
		}
	}

	@Test
	public void get() {
		String id = "id";
		DbEntityType dbEntityType = new DbEntityType();
		when(entityTypeDao.get(id)).thenReturn(dbEntityType);

		EntityType actualEntityType = testObj.get(id);

		verify(entityTypeDao).get(id);
		verifyNoMoreInteractions(entityTypeDao);
		assertNotNull(actualEntityType);
		assertEquals(dbEntityType.getId(), actualEntityType.getId());
		assertEquals(dbEntityType.getName(), actualEntityType.getName());
		assertEquals(dbEntityType.getParentId(), actualEntityType.getParentId());
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
		EntityType entityTypeToSave = new EntityType();
		entityTypeToSave.setId(null);
		entityTypeToSave.setName("name");
		entityTypeToSave.setParentId("parentId");
		entityTypeToSave.setShow(true);
		when(entityTypeDao.save(any(DbEntityType.class))).thenReturn(true);

		testObj.save(entityTypeToSave);

		verify(entityTypeDao).save(dbEntityTypeCaptor.capture());
		verifyNoMoreInteractions(entityTypeDao);
		DbEntityType capturedDbEntityType = dbEntityTypeCaptor.getValue();
		assertNotNull(capturedDbEntityType);
		assertNotEquals(entityTypeToSave.getId(), capturedDbEntityType.getId());
		assertEquals(entityTypeToSave.getParentId(), capturedDbEntityType.getParentId());
		assertEquals(entityTypeToSave.getName(), capturedDbEntityType.getName());
		assertEquals(entityTypeToSave.isShow(), capturedDbEntityType.isShow());
	}

	@Test
	public void save_new_daoFail() throws Exception {
		expectedException.expect(InternalServerException.class);
		EntityType entityTypeToSave = new EntityType();
		entityTypeToSave.setId(null);
		entityTypeToSave.setName("name");
		entityTypeToSave.setParentId("parentId");
		entityTypeToSave.setShow(true);
		expectedException.expectMessage("Unable to save entityType=" + entityTypeToSave);
		when(entityTypeDao.save(any(DbEntityType.class))).thenReturn(false);

		testObj.save(entityTypeToSave);
	}

	@Test
	public void save_new_checkReturn() throws Exception {
		EntityType entityTypeToSave = new EntityType();
		entityTypeToSave.setId(null);
		entityTypeToSave.setName("name");
		entityTypeToSave.setParentId("parentId");
		entityTypeToSave.setShow(true);
		when(entityTypeDao.save(any(DbEntityType.class))).thenReturn(true);

		EntityType actualEntityType = testObj.save(entityTypeToSave);

		verify(entityTypeDao).save(any(DbEntityType.class));
		verifyZeroInteractions(entityTypeDao);
		assertNotNull(actualEntityType);
		assertNotEquals(entityTypeToSave.getId(), actualEntityType.getId());
		assertEquals(entityTypeToSave.getParentId(), actualEntityType.getParentId());
		assertEquals(entityTypeToSave.getName(), actualEntityType.getName());
		assertEquals(entityTypeToSave.isShow(), actualEntityType.isShow());
	}

	@Test
	public void save_hasId_notExists() throws Exception {
		EntityType entityTypeToSave = new EntityType();
		entityTypeToSave.setId("id");
		entityTypeToSave.setName("name");
		entityTypeToSave.setParentId("parentId");
		entityTypeToSave.setShow(true);
		when(entityTypeDao.get(entityTypeToSave.getId())).thenReturn(null);
		when(entityTypeDao.save(any(DbEntityType.class))).thenReturn(true);

		EntityType actualEntityType = testObj.save(entityTypeToSave);

		verify(entityTypeDao).get(entityTypeToSave.getId());
		verify(entityTypeDao).save(any(DbEntityType.class));
		verifyNoMoreInteractions(entityTypeDao);
		assertNotNull(actualEntityType);
		assertEquals(entityTypeToSave.getId(), actualEntityType.getId());
		assertEquals(entityTypeToSave.getParentId(), actualEntityType.getParentId());
		assertEquals(entityTypeToSave.getName(), actualEntityType.getName());
		assertEquals(entityTypeToSave.isShow(), actualEntityType.isShow());
	}

	@Test
	public void save_hasId_exists() throws Exception {
		EntityType entityTypeToSave = new EntityType();
		entityTypeToSave.setId("id");
		entityTypeToSave.setName("name");
		entityTypeToSave.setParentId("parentId");
		entityTypeToSave.setShow(true);
		when(entityTypeDao.get(entityTypeToSave.getId())).thenReturn(new DbEntityType(entityTypeToSave));
		when(entityTypeDao.save(any(DbEntityType.class))).thenReturn(true);

		EntityType actualEntityType = testObj.save(entityTypeToSave);

		verify(entityTypeDao).get(entityTypeToSave.getId());
		verify(entityTypeDao).save(any(DbEntityType.class));
		verifyNoMoreInteractions(entityTypeDao);
		assertNotNull(actualEntityType);
		assertEquals(entityTypeToSave.getId(), actualEntityType.getId());
		assertEquals(entityTypeToSave.getParentId(), actualEntityType.getParentId());
		assertEquals(entityTypeToSave.getName(), actualEntityType.getName());
		assertEquals(entityTypeToSave.isShow(), actualEntityType.isShow());
	}

	DbEntityType createDbEntityType() {
		DbEntityType dbEntityType = new DbEntityType();
		dbEntityType.setId(UUID.randomUUID().toString());
		dbEntityType.setName(UUID.randomUUID().toString());
		dbEntityType.setParentId(UUID.randomUUID().toString());
		return dbEntityType;
	}
}
