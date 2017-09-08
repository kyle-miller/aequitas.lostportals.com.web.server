package com.lostportals.aequitas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lostportals.aequitas.db.dao.NoteDao;
import com.lostportals.aequitas.db.domain.DbNote;
import com.lostportals.aequitas.exception.InternalServerException;
import com.lostportals.aequitas.exception.NotFoundException;
import com.lostportals.aequitas.exception.UnprocessableEntityException;
import com.lostportals.aequitas.web.admin.domain.Note;

@Service
public class NoteService {

	private final NoteDao noteDao;

	@Autowired
	public NoteService(NoteDao noteDao) {
		this.noteDao = noteDao;
	}

	public List<Note> getAll() {
		List<DbNote> dbNoteList = noteDao.getAll();

		List<Note> noteList = new ArrayList<>();

		if (!CollectionUtils.isEmpty(dbNoteList)) {
			dbNoteList.stream().map(Note::new).forEach(noteList::add);
		}

		return noteList;
	}

	public Note get(String id) {
		DbNote dbNote = noteDao.get(id);

		if (dbNote == null) {
			throw new NotFoundException("Cannot find Note for id=" + id);
		}

		return new Note(dbNote);
	}

	public Note save(Note noteToSave) {
		DbNote dbNote = new DbNote(noteToSave);

		if (StringUtils.isBlank(dbNote.getId())) {
			dbNote.setId(UUID.randomUUID().toString());
		}

		try {
			noteDao.save(dbNote);
		} catch (DataAccessException | IllegalAccessException e) {
			throw new InternalServerException("Unable to save note=" + noteToSave, e);
		}

		return new Note(dbNote);
	}

	public void delete(String id) {
		if (id == null) {
			throw new UnprocessableEntityException("id is required");
		} else if (get(id) != null) {
			noteDao.delete(id);	
		}
	}
}
