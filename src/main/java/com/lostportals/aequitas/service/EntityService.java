package com.lostportals.aequitas.service;

import java.util.List;

import com.lostportals.aequitas.web.admin.domain.Entity;

public interface EntityService {
	List<Entity> getAll();

	Entity get(String id);

	Entity save(Entity entity);
}
