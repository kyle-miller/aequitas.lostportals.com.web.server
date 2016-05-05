package com.lostportals.aequitas.service;

import java.util.List;

import com.lostportals.aequitas.web.admin.domain.EntityType;

public interface EntityTypeService {
	List<EntityType> getAll();

	EntityType get(String id);

	EntityType save(EntityType entityType);
}
