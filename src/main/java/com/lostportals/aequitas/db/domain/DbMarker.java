package com.lostportals.aequitas.db.domain;

import java.math.BigDecimal;

import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lostportals.aequitas.web.admin.domain.Marker;

public class DbMarker extends SqlType {
	private String id;
	private String entityId;
	private String iconId;
	private BigDecimal latitude;
	private BigDecimal longitude;

	public DbMarker() {

	}

	public DbMarker(Marker marker) {
		this.id = marker.getId();
		this.entityId = marker.getEntityId();
		this.iconId = marker.getIconId();
		this.latitude = marker.getLatitude();
		this.longitude = marker.getLongitude();
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

	public String getIconId() {
		return iconId;
	}

	public void setIconId(String iconId) {
		this.iconId = iconId;
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

	public String toString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return new ToStringCreator(this).toString();
		}
	}
}
