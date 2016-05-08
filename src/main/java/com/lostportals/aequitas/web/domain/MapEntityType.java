package com.lostportals.aequitas.web.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lostportals.aequitas.web.admin.domain.EntityType;

public class MapEntityType {
	private String id;
	private String parentId;
	private String name;
	private boolean show;
	private List<MapImage> images = new ArrayList<>();

	public MapEntityType() {

	}

	public MapEntityType(EntityType entityType) {
		this.id = entityType.getId();
		this.parentId = entityType.getParentId();
		this.name = entityType.getName();
		this.show = entityType.isShow();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public void addImage(MapImage mapImage) {
		if (images == null) {
			images = new ArrayList<>();
		}

		if (!images.contains(mapImage)) {
			images.add(mapImage);
		}
	}

	public List<MapImage> getImages() {
		return images;
	}

	public void setImages(List<MapImage> images) {
		this.images = images;
	}

	public String toString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return new ToStringCreator(this).toString();
		}
	}
}
