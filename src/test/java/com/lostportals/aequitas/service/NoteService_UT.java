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

import com.lostportals.aequitas.exception.UnprocessableEntityException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.db.dao.NoteDao;
import com.lostportals.aequitas.db.domain.DbNote;
import com.lostportals.aequitas.exception.InternalServerException;
import com.lostportals.aequitas.exception.NotFoundException;
import com.lostportals.aequitas.web.admin.domain.Note;

@RunWith(MockitoJUnitRunner.class)
public class NoteService_UT {

	@InjectMocks
	NoteService testObj;

	@Mock
	NoteDao noteDao;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Captor
	ArgumentCaptor<DbNote> dbNoteCaptor;

	@Test
	public void getAll() {
		List<DbNote> daoList = Arrays.asList(createDbNote(), createDbNote(), createDbNote());
		when(noteDao.getAll()).thenReturn(daoList);

		List<Note> actualList = testObj.getAll();

		verify(noteDao).getAll();
		verifyNoMoreInteractions(noteDao);
		assertNotNull(actualList);
		assertEquals(daoList.size(), actualList.size());
		for (int i = 0; i < daoList.size(); i++) {
			assertEquals(daoList.get(i).getId(), actualList.get(i).getId());
			assertEquals(daoList.get(i).getEntityId(), actualList.get(i).getEntityId());
			assertEquals(daoList.get(i).getNote(), actualList.get(i).getNote());
			assertEquals(daoList.get(i).getPosition(), actualList.get(i).getPosition());
		}
	}

	DbNote createDbNote() {
		DbNote dbObj = new DbNote();
		dbObj.setId(UUID.randomUUID().toString());
		dbObj.setEntityId(UUID.randomUUID().toString());
		dbObj.setNote(UUID.randomUUID().toString());
		dbObj.setPosition(Double.valueOf(Math.random() * 100000).intValue());
		return dbObj;
	}

	@Test
	public void get() {
		String id = "id";
		DbNote dbObj = createDbNote();
		when(noteDao.get(id)).thenReturn(dbObj);

		Note actualObj = testObj.get(id);

		verify(noteDao).get(id);
		verifyNoMoreInteractions(noteDao);
		assertNotNull(actualObj);
		assertEquals(dbObj.getId(), actualObj.getId());
		assertEquals(dbObj.getEntityId(), actualObj.getEntityId());
		assertEquals(dbObj.getNote(), actualObj.getNote());
		assertEquals(dbObj.getPosition(), actualObj.getPosition());
	}

	@Test
	public void get_notFound() {
		expectedException.expect(NotFoundException.class);
		String id = "id";
		String expectedMessage = "Cannot find Note for id=" + id;
		expectedException.expectMessage(expectedMessage);
		when(noteDao.get(id)).thenReturn(null);

		testObj.get(id);
	}

	@Test
	public void save_new_checkDaoCall() throws Exception {
		Note toSave = new Note(createDbNote());
		toSave.setId(null);

		testObj.save(toSave);

		verify(noteDao).save(dbNoteCaptor.capture());
		verifyNoMoreInteractions(noteDao);
		DbNote capturedDbObj = dbNoteCaptor.getValue();
		assertNotNull(capturedDbObj);
		assertNotEquals(toSave.getId(), capturedDbObj.getId());
		assertEquals(toSave.getEntityId(), capturedDbObj.getEntityId());
		assertEquals(toSave.getNote(), capturedDbObj.getNote());
		assertEquals(toSave.getPosition(), capturedDbObj.getPosition());
	}

	@Test
	public void save_new_daoFail() throws Exception {
		expectedException.expect(InternalServerException.class);
		Note toSave = new Note(createDbNote());
		toSave.setId(null);
		expectedException.expectMessage("Unable to save note=" + toSave);
		when(noteDao.save(any(DbNote.class))).thenThrow(new IllegalAccessException("something"));

		testObj.save(toSave);
	}

	@Test
	public void save_new_checkReturn() throws Exception {
		Note toSave = new Note(createDbNote());
		toSave.setId(null);

		Note actualObj = testObj.save(toSave);

		assertNotNull(actualObj);
		assertNotEquals(toSave.getId(), actualObj.getId());
		assertEquals(toSave.getEntityId(), actualObj.getEntityId());
		assertEquals(toSave.getNote(), actualObj.getNote());
		assertEquals(toSave.getPosition(), actualObj.getPosition());
	}

	@Test
	public void save_hasId_checkDaoCall() throws Exception {
		Note toSave = new Note(createDbNote());

		testObj.save(toSave);

		verify(noteDao).save(dbNoteCaptor.capture());
		verifyNoMoreInteractions(noteDao);
		DbNote capturedDbObj = dbNoteCaptor.getValue();
		assertNotNull(capturedDbObj);
		assertEquals(toSave.getId(), capturedDbObj.getId());
		assertEquals(toSave.getEntityId(), capturedDbObj.getEntityId());
		assertEquals(toSave.getNote(), capturedDbObj.getNote());
		assertEquals(toSave.getPosition(), capturedDbObj.getPosition());
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
		when(noteDao.get(id)).thenReturn(new DbNote());

		testObj.delete(id);

		verify(noteDao).delete(id);
	}
}
