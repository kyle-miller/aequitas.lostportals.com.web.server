package com.lostportals.aequitas.web.domain;

import java.util.List;

import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lostportals.aequitas.web.db.domain.Entity;

public class MapEntity {
	private String id;
	private String title;
	private MapEntityType type;
	private List<MapCircle> circles;
	private List<MapMarker> markers;
	private List<MapPolygon> polygons;
	private List<MapNote> notes;

	public MapEntity() {

	}

	public MapEntity(Entity entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
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

	public MapEntityType getType() {
		return type;
	}

	public void setType(MapEntityType type) {
		this.type = type;
	}

	public List<MapCircle> getCircles() {
		return circles;
	}

	public void setCircles(List<MapCircle> circles) {
		this.circles = circles;
	}

	public List<MapMarker> getMarkers() {
		return markers;
	}

	public void setMarkers(List<MapMarker> markers) {
		this.markers = markers;
	}

	public List<MapPolygon> getPolygons() {
		return polygons;
	}

	public void setPolygons(List<MapPolygon> polygons) {
		this.polygons = polygons;
	}

	public List<MapNote> getNotes() {
		return notes;
	}

	public void setNotes(List<MapNote> notes) {
		this.notes = notes;
	}

	public String toString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return new ToStringCreator(this).toString();
		}
	}
}
