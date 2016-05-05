package com.lostportals.aequitas.db.domain;

import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lostportals.aequitas.web.db.domain.Polygon;

public class DbPolygon extends SqlType {
	private String id;
	private String entityId;
	private String vertices;

	public DbPolygon() {

	}

	public DbPolygon(Polygon polygon) {
		this.id = polygon.getId();
		this.entityId = polygon.getEntityId();
		this.vertices = polygon.getVertices();
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
