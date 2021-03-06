package com.lostportals.aequitas.web.admin.domain;

import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lostportals.aequitas.db.domain.DbEntity;
import com.lostportals.aequitas.web.domain.MapEntity;

public class Entity {
	private String id;
	private String title;

	public Entity() {

	}

	public Entity(DbEntity dbEntity) {
		this.id = dbEntity.getId();
		this.title = dbEntity.getTitle();
	}

	public Entity(MapEntity mapEntity) {
		this.id = mapEntity.getId();
		this.title = mapEntity.getTitle();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String toString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return new ToStringCreator(this).toString();
		}
	}
}
