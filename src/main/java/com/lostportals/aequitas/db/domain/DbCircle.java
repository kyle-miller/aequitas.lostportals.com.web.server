package com.lostportals.aequitas.db.domain;

import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lostportals.aequitas.web.admin.domain.Circle;

public class DbCircle extends SqlType {
	private String id;
	private String entityId;
	private Double latitude;
	private Double longitude;
	private Integer radius;
	private String outlineColor;
	private String fillColor;

	public DbCircle() {

	}

	public DbCircle(Circle circle) {
		this.id = circle.getId();
		this.entityId = circle.getEntityId();
		this.latitude = circle.getLatitude() == null ? null : circle.getLatitude().doubleValue();
		this.longitude = circle.getLongitude() == null ? null : circle.getLongitude().doubleValue();
		this.radius = circle.getRadius();
		this.outlineColor = circle.getOutlineColor();
		this.fillColor = circle.getFillColor();
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

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getRadius() {
		return radius;
	}

	public void setRadius(Integer radius) {
		this.radius = radius;
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
