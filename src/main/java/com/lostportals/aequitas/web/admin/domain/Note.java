package com.lostportals.aequitas.web.admin.domain;

import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lostportals.aequitas.db.domain.DbNote;
import com.lostportals.aequitas.web.domain.MapNote;

public class Note {
	private String id;
	private String entityId;
	private String note;
	private Integer position;

	public Note() {

	}

	public Note(DbNote dbNote) {
		this.id = dbNote.getId();
		this.entityId = dbNote.getEntityId();
		this.note = dbNote.getNote();
		this.position = dbNote.getPosition();
	}

	public Note(MapNote mapNote) {
		this.id = mapNote.getId();
		this.entityId = mapNote.getEntityId();
		this.note = mapNote.getNote();
		this.position = mapNote.getPosition();
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
