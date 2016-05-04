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

import com.lostportals.aequitas.db.dao.IconDao;
import com.lostportals.aequitas.db.domain.DbIcon;
import com.lostportals.aequitas.exception.InternalServerException;
import com.lostportals.aequitas.exception.NotFoundException;
import com.lostportals.aequitas.web.domain.Icon;

@RunWith(MockitoJUnitRunner.class)
public class IconServiceImpl_UT {

	@InjectMocks
	IconServiceImpl testObj;

	@Mock
	IconDao iconDao;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Captor
	ArgumentCaptor<DbIcon> dbIconCaptor;

	@Test
	public void getAll() {
		List<DbIcon> daoList = Arrays.asList(createDbIcon(), createDbIcon(), createDbIcon());
		when(iconDao.getAll()).thenReturn(daoList);

		List<Icon> actualList = testObj.getAll();

		verify(iconDao).getAll();
		verifyNoMoreInteractions(iconDao);
		assertNotNull(actualList);
		assertEquals(daoList.size(), actualList.size());
		for (int i = 0; i < daoList.size(); i++) {
			assertEquals(daoList.get(i).getId(), actualList.get(i).getId());
			assertEquals(daoList.get(i).getName(), actualList.get(i).getName());
			assertEquals(daoList.get(i).getUrl(), actualList.get(i).getUrl());
		}
	}

	@Test
	public void get() {
		String id = "id";
		DbIcon dbObj = new DbIcon();
		when(iconDao.get(id)).thenReturn(dbObj);

		Icon actualObj = testObj.get(id);

		verify(iconDao).get(id);
		verifyNoMoreInteractions(iconDao);
		assertNotNull(actualObj);
		assertEquals(dbObj.getId(), actualObj.getId());
		assertEquals(dbObj.getName(), actualObj.getName());
		assertEquals(dbObj.getUrl(), actualObj.getUrl());
	}

	@Test
	public void get_notFound() {
		expectedException.expect(NotFoundException.class);
		String id = "id";
		String expectedMessage = "Cannot find Icon for id=" + id;
		expectedException.expectMessage(expectedMessage);
		when(iconDao.get(id)).thenReturn(null);

		testObj.get(id);
	}

	@Test
	public void save_new_checkDaoCall() throws Exception {
		Icon toSave = new Icon();
		toSave.setId(null);
		toSave.setName("name");
		toSave.setUrl("parentId");
		when(iconDao.save(any(DbIcon.class))).thenReturn(true);

		testObj.save(toSave);

		verify(iconDao).save(dbIconCaptor.capture());
		verifyNoMoreInteractions(iconDao);
		DbIcon capturedDbObj = dbIconCaptor.getValue();
		assertNotNull(capturedDbObj);
		assertNotEquals(toSave.getId(), capturedDbObj.getId());
		assertEquals(toSave.getUrl(), capturedDbObj.getUrl());
		assertEquals(toSave.getName(), capturedDbObj.getName());
	}

	@Test
	public void save_new_daoFail() throws Exception {
		expectedException.expect(InternalServerException.class);
		Icon toSave = new Icon();
		toSave.setId(null);
		toSave.setName("name");
		toSave.setUrl("parentId");
		expectedException.expectMessage("Unable to save icon=" + toSave);
		when(iconDao.save(any(DbIcon.class))).thenReturn(false);

		testObj.save(toSave);
	}

	@Test
	public void save_new_checkReturn() throws Exception {
		Icon toSave = new Icon();
		toSave.setId(null);
		toSave.setName("name");
		toSave.setUrl("parentId");
		when(iconDao.save(any(DbIcon.class))).thenReturn(true);

		Icon actualObj = testObj.save(toSave);

		verify(iconDao).save(any(DbIcon.class));
		verifyZeroInteractions(iconDao);
		assertNotNull(actualObj);
		assertNotEquals(toSave.getId(), actualObj.getId());
		assertEquals(toSave.getUrl(), actualObj.getUrl());
		assertEquals(toSave.getName(), actualObj.getName());
	}

	@Test
	public void save_hasId_notExists() throws Exception {
		Icon toSave = new Icon();
		toSave.setId("id");
		toSave.setName("name");
		toSave.setUrl("parentId");
		when(iconDao.get(toSave.getId())).thenReturn(null);
		when(iconDao.save(any(DbIcon.class))).thenReturn(true);

		Icon actualObj = testObj.save(toSave);

		verify(iconDao).get(toSave.getId());
		verify(iconDao).save(any(DbIcon.class));
		verifyNoMoreInteractions(iconDao);
		assertNotNull(actualObj);
		assertEquals(toSave.getId(), actualObj.getId());
		assertEquals(toSave.getUrl(), actualObj.getUrl());
		assertEquals(toSave.getName(), actualObj.getName());
	}

	@Test
	public void save_hasId_exists() throws Exception {
		Icon toSave = new Icon();
		toSave.setId("id");
		toSave.setName("name");
		toSave.setUrl("parentId");
		when(iconDao.get(toSave.getId())).thenReturn(new DbIcon(toSave));
		when(iconDao.save(any(DbIcon.class))).thenReturn(true);

		Icon actualObj = testObj.save(toSave);

		verify(iconDao).get(toSave.getId());
		verify(iconDao).save(any(DbIcon.class));
		verifyNoMoreInteractions(iconDao);
		assertNotNull(actualObj);
		assertEquals(toSave.getId(), actualObj.getId());
		assertEquals(toSave.getUrl(), actualObj.getUrl());
		assertEquals(toSave.getName(), actualObj.getName());
	}

	DbIcon createDbIcon() {
		DbIcon dbObj = new DbIcon();
		dbObj.setId(UUID.randomUUID().toString());
		dbObj.setName(UUID.randomUUID().toString());
		dbObj.setUrl(UUID.randomUUID().toString());
		return dbObj;
	}
}
