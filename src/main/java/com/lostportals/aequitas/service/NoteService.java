package com.lostportals.aequitas.service;

import java.util.List;

import com.lostportals.aequitas.web.domain.Note;

public interface NoteService {
	List<Note> getAll();

	Note get(String id);

	Note save(Note note);
}
