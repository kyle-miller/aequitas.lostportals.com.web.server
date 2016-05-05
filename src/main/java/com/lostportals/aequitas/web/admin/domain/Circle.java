package com.lostportals.aequitas.web.admin.domain;

import java.math.BigDecimal;

import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lostportals.aequitas.db.domain.DbCircle;

public class Circle {
	private String id;
	private String entityId;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private Integer radius;
	private String outlineColor;
	private String fillColor;

	public Circle() {

	}

	public Circle(DbCircle dbCircle) {
		this.id = dbCircle.getId();
		this.entityId = dbCircle.getEntityId();
		this.latitude = dbCircle.getLatitude();
		this.longitude = dbCircle.getLongitude();
		this.radius = dbCircle.getRadius();
		this.outlineColor = dbCircle.getOutlineColor();
		this.fillColor = dbCircle.getFillColor();
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

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
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
