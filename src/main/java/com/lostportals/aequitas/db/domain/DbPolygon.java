package com.lostportals.aequitas.db.domain;

import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lostportals.aequitas.web.admin.domain.Polygon;

public class DbPolygon extends SqlType {
	private String id;
	private String entityId;
	private String vertices;
	private String outlineColor;
	private String fillColor;

	public DbPolygon() {

	}

	public DbPolygon(Polygon polygon) {
		this.id = polygon.getId();
		this.entityId = polygon.getEntityId();
		this.vertices = polygon.getVertices();
		this.outlineColor = polygon.getOutlineColor();
		this.fillColor = polygon.getFillColor();
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

	public String getOutlineColor() {
		return outlineColor;
	}

	public void setOutlineColor(String outlineColor) {
		this.outlineColor = outlineColor;
	}

	public String getFillColor() {
		return fillColor;
	}

	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;
	}

	public String toString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return new ToStringCreator(this).toString();
		}
	}
}
