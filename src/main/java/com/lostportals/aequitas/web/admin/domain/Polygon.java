package com.lostportals.aequitas.web.admin.domain;

import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lostportals.aequitas.db.domain.DbPolygon;
import com.lostportals.aequitas.web.domain.MapPolygon;

public class Polygon {
	private String id;
	private String entityId;
	private String vertices;
	private String outlineColor;
	private String fillColor;

	public Polygon() {

	}

	public Polygon(DbPolygon dbPolygon) {
		this.id = dbPolygon.getId();
		this.entityId = dbPolygon.getEntityId();
		this.vertices = dbPolygon.getVertices();
		this.outlineColor = dbPolygon.getOutlineColor();
		this.fillColor = dbPolygon.getFillColor();
	}

	public Polygon(MapPolygon mapPolygon) {
		this.id = mapPolygon.getId();
		this.entityId = mapPolygon.getEntityId();
		this.vertices = mapPolygon.getVertices();
		this.outlineColor = mapPolygon.getOutlineColor();
		this.fillColor = mapPolygon.getFillColor();
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
