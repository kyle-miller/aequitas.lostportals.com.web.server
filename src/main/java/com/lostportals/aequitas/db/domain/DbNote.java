package com.lostportals.aequitas.db.domain;

import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lostportals.aequitas.web.db.domain.Note;

public class DbNote extends SqlType {
	private String id;
	private String entityId;
	private String note;
	private Integer position;

	public DbNote() {

	}

	public DbNote(Note note) {
		this.id = note.getId();
		this.entityId = note.getEntityId();
		this.note = note.getNote();
		this.position = note.getPosition();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String toString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return new ToStringCreator(this).toString();
		}
	}
}
