package com.lostportals.aequitas.web.db.domain;

import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lostportals.aequitas.db.domain.DbPolygon;

public class Polygon {
	private String id;
	private String entityId;
	private String vertices;

	public Polygon() {

	}

	public Polygon(DbPolygon dbPolygon) {
		this.id = dbPolygon.getId();
		this.entityId = dbPolygon.getEntityId();
		this.vertices = dbPolygon.getVertices();
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

	public String getVertices() {
		return vertices;
	}

	public void setVertices(String vertices) {
		this.vertices = vertices;
	}

	public String toString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return new ToStringCreator(this).toString();
		}
	}
}
