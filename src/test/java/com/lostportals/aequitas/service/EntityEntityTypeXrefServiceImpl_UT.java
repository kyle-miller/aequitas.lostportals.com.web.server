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

import com.lostportals.aequitas.db.dao.EntityEntityTypeXrefDao;
import com.lostportals.aequitas.db.domain.DbEntityEntityTypeXref;
import com.lostportals.aequitas.exception.InternalServerException;
import com.lostportals.aequitas.exception.NotFoundException;
import com.lostportals.aequitas.web.admin.domain.EntityEntityTypeXref;

@RunWith(MockitoJUnitRunner.class)
public class EntityEntityTypeXrefServiceImpl_UT {

	@InjectMocks
	EntityEntityTypeXrefServiceImpl testObj;

	@Mock
	EntityEntityTypeXrefDao entityEntityTypeXrefDao;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Captor
	ArgumentCaptor<DbEntityEntityTypeXref> dbEntityEntityTypeXrefCaptor;

	@Test
	public void getAll() {
		List<DbEntityEntityTypeXref> daoList = Arrays.asList(createDbEntityEntityTypeXref(), createDbEntityEntityTypeXref(), createDbEntityEntityTypeXref());
		when(entityEntityTypeXrefDao.getAll()).thenReturn(daoList);

		List<EntityEntityTypeXref> actualList = testObj.getAll();

		verify(entityEntityTypeXrefDao).getAll();
		verifyNoMoreInteractions(entityEntityTypeXrefDao);
		assertNotNull(actualList);
		assertEquals(daoList.size(), actualList.size());
		for (int i = 0; i < daoList.size(); i++) {
			assertEquals(daoList.get(i).getId(), actualList.get(i).getId());
			assertEquals(daoList.get(i).getEntityId(), actualList.get(i).getEntityId());
			assertEquals(daoList.get(i).getEntityTypeId(), actualList.get(i).getEntityTypeId());
		}
	}

	DbEntityEntityTypeXref createDbEntityEntityTypeXref() {
		DbEntityEntityTypeXref dbObj = new DbEntityEntityTypeXref();
		dbObj.setId(UUID.randomUUID().toString());
		dbObj.setEntityId(UUID.randomUUID().toString());
		dbObj.setEntityTypeId(UUID.randomUUID().toString());
		return dbObj;
	}

	@Test
	public void get() {
		String id = "id";
		DbEntityEntityTypeXref dbObj = createDbEntityEntityTypeXref();
		when(entityEntityTypeXrefDao.get(id)).thenReturn(dbObj);

		EntityEntityTypeXref actualObj = testObj.get(id);

		verify(entityEntityTypeXrefDao).get(id);
		verifyNoMoreInteractions(entityEntityTypeXrefDao);
		assertNotNull(actualObj);
		assertEquals(dbObj.getId(), actualObj.getId());
		assertEquals(dbObj.getEntityId(), actualObj.getEntityId());
		assertEquals(dbObj.getEntityTypeId(), actualObj.getEntityTypeId());
	}

	@Test
	public void get_notFound() {
		expectedException.expect(NotFoundException.class);
		String id = "id";
		String expectedMessage = "Cannot find EntityEntityTypeXref for id=" + id;
		expectedException.expectMessage(expectedMessage);
		when(entityEntityTypeXrefDao.get(id)).thenReturn(null);

		testObj.get(id);
	}

	@Test
	public void save_new_checkDaoCall() throws Exception {
		EntityEntityTypeXref toSave = new EntityEntityTypeXref(createDbEntityEntityTypeXref());
		toSave.setId(null);

		testObj.save(toSave);

		verify(entityEntityTypeXrefDao).save(dbEntityEntityTypeXrefCaptor.capture());
		verifyNoMoreInteractions(entityEntityTypeXrefDao);
		DbEntityEntityTypeXref capturedDbObj = dbEntityEntityTypeXrefCaptor.getValue();
		assertNotNull(capturedDbObj);
		assertNotEquals(toSave.getId(), capturedDbObj.getId());
		assertEquals(toSave.getEntityTypeId(), capturedDbObj.getEntityTypeId());
		assertEquals(toSave.getEntityId(), capturedDbObj.getEntityId());
	}

	@Test
	public void save_new_daoFail() throws Exception {
		expectedException.expect(InternalServerException.class);
		EntityEntityTypeXref toSave = new EntityEntityTypeXref(createDbEntityEntityTypeXref());
		toSave.setId(null);
		expectedException.expectMessage("Unable to save entityEntityTypeXref=" + toSave);
		when(entityEntityTypeXrefDao.save(any(DbEntityEntityTypeXref.class))).thenThrow(new IllegalAccessException("something"));

		testObj.save(toSave);
	}

	@Test
	public void save_new_checkReturn() throws Exception {
		EntityEntityTypeXref toSave = new EntityEntityTypeXref(createDbEntityEntityTypeXref());
		toSave.setId(null);

		EntityEntityTypeXref actualObj = testObj.save(toSave);

		assertNotNull(actualObj);
		assertNotEquals(toSave.getId(), actualObj.getId());
		assertEquals(toSave.getEntityTypeId(), actualObj.getEntityTypeId());
		assertEquals(toSave.getEntityId(), actualObj.getEntityId());
	}

	@Test
	public void save_hasId_checkDaoCall() throws Exception {
		EntityEntityTypeXref toSave = new EntityEntityTypeXref(createDbEntityEntityTypeXref());

		testObj.save(toSave);

		verify(entityEntityTypeXrefDao).save(dbEntityEntityTypeXrefCaptor.capture());
		verifyNoMoreInteractions(entityEntityTypeXrefDao);
		DbEntityEntityTypeXref capturedDbObj = dbEntityEntityTypeXrefCaptor.getValue();
		assertNotNull(capturedDbObj);
		assertEquals(toSave.getId(), capturedDbObj.getId());
		assertEquals(toSave.getEntityTypeId(), capturedDbObj.getEntityTypeId());
		assertEquals(toSave.getEntityId(), capturedDbObj.getEntityId());
	}
}
