package com.lostportals.aequitas.web.admin.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lostportals.aequitas.service.NoteService;
import com.lostportals.aequitas.web.admin.domain.Note;

@RestController
@RequestMapping(value = "/api/admin/notes", produces = { "application/json" })
public class NoteController {

	private final NoteService noteService;

	@Autowired
	public NoteController(NoteService noteService) {
		this.noteService = noteService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> post(HttpServletRequest request, @RequestBody Note note) {
		note = noteService.save(note);

		URI newEntityUrl = URI.create(request.getRequestURI().replaceFirst("^(.*)/?$", "$1/" + note.getId()));

		return ResponseEntity.created(newEntityUrl).build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Note> getAll() {
		return noteService.getAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Note get(@PathVariable String id) {
		return noteService.get(id);
	}
}
