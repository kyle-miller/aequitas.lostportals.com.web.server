package com.lostportals.aequitas.web.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lostportals.aequitas.web.admin.domain.Entity;

public class MapEntity {
	private String id;
	private String title;
	private List<MapEntityType> types = new ArrayList<>();
	private List<MapCircle> circles = new ArrayList<>();
	private List<MapMarker> markers = new ArrayList<>();
	private List<MapPolygon> polygons = new ArrayList<>();
	private List<MapNote> notes = new ArrayList<>();

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

	public void addType(MapEntityType mapEntityType) {
		if (types == null) {
			types = new ArrayList<>();
		}

		if (!types.contains(mapEntityType)) {
			types.add(mapEntityType);
		}
	}

	public List<MapEntityType> getTypes() {
		return types;
	}

	public void setTypes(List<MapEntityType> types) {
		this.types = types;
	}

	public void addCircle(MapCircle mapCircle) {
		if (circles == null) {
			circles = new ArrayList<>();
		}

		if (!circles.contains(mapCircle)) {
			circles.add(mapCircle);
		}
	}

	public List<MapCircle> getCircles() {
		return circles;
	}

	public void setCircles(List<MapCircle> circles) {
		this.circles = circles;
	}

	public void addMarker(MapMarker mapMarker) {
		if (markers == null) {
			markers = new ArrayList<>();
		}

		if (!markers.contains(mapMarker)) {
			markers.add(mapMarker);
		}
	}

	public List<MapMarker> getMarkers() {
		return markers;
	}

	public void setMarkers(List<MapMarker> markers) {
		this.markers = markers;
	}

	public void addPolygon(MapPolygon mapPolygon) {
		if (polygons == null) {
			polygons = new ArrayList<>();
		}

		if (!polygons.contains(mapPolygon)) {
			polygons.add(mapPolygon);
		}
	}

	public List<MapPolygon> getPolygons() {
		return polygons;
	}

	public void setPolygons(List<MapPolygon> polygons) {
		this.polygons = polygons;
	}

	public void addNote(MapNote mapNote) {
		if (notes == null) {
			notes = new ArrayList<>();
		}

		if (!notes.contains(mapNote)) {
			notes.add(mapNote);
		}
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
