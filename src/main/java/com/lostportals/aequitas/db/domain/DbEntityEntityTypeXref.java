package com.lostportals.aequitas.db.domain;

import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lostportals.aequitas.web.domain.EntityEntityTypeXref;

public class DbEntityEntityTypeXref extends SqlType {
	private String id;
	private String entityTypeId;
	private String entityId;

	public DbEntityEntityTypeXref() {

	}

	public DbEntityEntityTypeXref(EntityEntityTypeXref entityEntityTypeXref) {
		this.id = entityEntityTypeXref.getId();
		this.entityId = entityEntityTypeXref.getEntityId();
		this.entityTypeId = entityEntityTypeXref.getEntityTypeId();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEntityTypeId() {
		return entityTypeId;
	}

	public void setEntityTypeId(String entityTypeId) {
		this.entityTypeId = entityTypeId;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String toString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return new ToStringCreator(this).toString();
		}
	}
}
