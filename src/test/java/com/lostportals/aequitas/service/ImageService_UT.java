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

import com.lostportals.aequitas.db.dao.ImageDao;
import com.lostportals.aequitas.db.domain.DbImage;
import com.lostportals.aequitas.exception.InternalServerException;
import com.lostportals.aequitas.exception.NotFoundException;
import com.lostportals.aequitas.web.admin.domain.Image;

@RunWith(MockitoJUnitRunner.class)
public class ImageService_UT {

	@InjectMocks
	ImageService testObj;

	@Mock
	ImageDao imageDao;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Captor
	ArgumentCaptor<DbImage> dbImageCaptor;

	@Test
	public void getAll() {
		List<DbImage> daoList = Arrays.asList(createDbImage(), createDbImage(), createDbImage());
		when(imageDao.getAll()).thenReturn(daoList);

		List<Image> actualList = testObj.getAll();

		verify(imageDao).getAll();
		verifyNoMoreInteractions(imageDao);
		assertNotNull(actualList);
		assertEquals(daoList.size(), actualList.size());
		for (int i = 0; i < daoList.size(); i++) {
			assertEquals(daoList.get(i).getId(), actualList.get(i).getId());
			assertEquals(daoList.get(i).getEntityId(), actualList.get(i).getEntityId());
			assertEquals(daoList.get(i).getUrl(), actualList.get(i).getUrl());
		}
	}

	DbImage createDbImage() {
		DbImage dbObj = new DbImage();
		dbObj.setId(UUID.randomUUID().toString());
		dbObj.setEntityId(UUID.randomUUID().toString());
		dbObj.setUrl(UUID.randomUUID().toString());
		return dbObj;
	}

	@Test
	public void get() {
		String id = "id";
		DbImage dbObj = createDbImage();
		when(imageDao.get(id)).thenReturn(dbObj);

		Image actualObj = testObj.get(id);

		verify(imageDao).get(id);
		verifyNoMoreInteractions(imageDao);
		assertNotNull(actualObj);
		assertEquals(dbObj.getId(), actualObj.getId());
		assertEquals(dbObj.getEntityId(), actualObj.getEntityId());
		assertEquals(dbObj.getUrl(), actualObj.getUrl());
	}

	@Test
	public void get_notFound() {
		expectedException.expect(NotFoundException.class);
		String id = "id";
		String expectedMessage = "Cannot find Image for id=" + id;
		expectedException.expectMessage(expectedMessage);
		when(imageDao.get(id)).thenReturn(null);

		testObj.get(id);
	}

	@Test
	public void save_new_checkDaoCall() throws Exception {
		Image toSave = new Image(createDbImage());
		toSave.setId(null);

		testObj.save(toSave);

		verify(imageDao).save(dbImageCaptor.capture());
		verifyNoMoreInteractions(imageDao);
		DbImage capturedDbObj = dbImageCaptor.getValue();
		assertNotNull(capturedDbObj);
		assertNotEquals(toSave.getId(), capturedDbObj.getId());
		assertEquals(toSave.getEntityId(), capturedDbObj.getEntityId());
		assertEquals(toSave.getUrl(), capturedDbObj.getUrl());
	}

	@Test
	public void save_new_daoFail() throws Exception {
		expectedException.expect(InternalServerException.class);
		Image toSave = new Image(createDbImage());
		toSave.setId(null);
		expectedException.expectMessage("Unable to save image=" + toSave);
		when(imageDao.save(any(DbImage.class))).thenThrow(new IllegalAccessException("something"));

		testObj.save(toSave);
	}

	@Test
	public void save_new_checkReturn() throws Exception {
		Image toSave = new Image(createDbImage());
		toSave.setId(null);

		Image actualObj = testObj.save(toSave);

		assertNotNull(actualObj);
		assertNotEquals(toSave.getId(), actualObj.getId());
		assertEquals(toSave.getEntityId(), actualObj.getEntityId());
		assertEquals(toSave.getUrl(), actualObj.getUrl());
	}

	@Test
	public void save_hasId_checkDaoCall() throws Exception {
		Image toSave = new Image(createDbImage());

		testObj.save(toSave);

		verify(imageDao).save(dbImageCaptor.capture());
		verifyNoMoreInteractions(imageDao);
		DbImage capturedDbObj = dbImageCaptor.getValue();
		assertNotNull(capturedDbObj);
		assertEquals(toSave.getId(), capturedDbObj.getId());
		assertEquals(toSave.getEntityId(), capturedDbObj.getEntityId());
		assertEquals(toSave.getUrl(), capturedDbObj.getUrl());
	}
}
