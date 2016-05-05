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
import com.lostportals.aequitas.web.admin.domain.Note;

@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	NoteDao noteDao;

	@Override
	public List<Note> getAll() {
		List<DbNote> dbNoteList = noteDao.getAll();

		List<Note> noteList = new ArrayList<>();

		if (!CollectionUtils.isEmpty(dbNoteList)) {
			dbNoteList.stream().map(Note::new).forEach(noteList::add);
		}

		return noteList;
	}

	@Override
	public Note get(String id) {
		DbNote dbNote = noteDao.get(id);

		if (dbNote == null) {
			throw new NotFoundException("Cannot find Note for id=" + id);
		}

		Note note = new Note(dbNote);

		return note;
	}

	@Override
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

		Note savedNote = new Note(dbNote);
		return savedNote;
	}
}
