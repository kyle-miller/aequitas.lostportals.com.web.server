package com.lostportals.aequitas.web.db.domain;

import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lostportals.aequitas.db.domain.DbEntityEntityTypeXref;

public class EntityEntityTypeXref {
	private String id;
	private String entityTypeId;
	private String entityId;

	public EntityEntityTypeXref() {

	}

	public EntityEntityTypeXref(DbEntityEntityTypeXref dbEntityEntityTypeXref) {
		this.id = dbEntityEntityTypeXref.getId();
		this.entityTypeId = dbEntityEntityTypeXref.getEntityTypeId();
		this.entityId = dbEntityEntityTypeXref.getEntityId();
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
