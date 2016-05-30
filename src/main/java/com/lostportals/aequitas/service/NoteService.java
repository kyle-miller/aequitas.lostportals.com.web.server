package com.lostportals.aequitas.service;

import java.util.List;

import com.lostportals.aequitas.web.admin.domain.Note;

public interface NoteService {
	List<Note> getAll();

	Note get(String id);

	Note save(Note note);

	void delete(String id);
}
