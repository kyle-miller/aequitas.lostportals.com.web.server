package com.lostportals.aequitas.service;

import java.util.List;

import com.lostportals.aequitas.web.admin.domain.EntityEntityTypeXref;

public interface EntityEntityTypeXrefService {
	List<EntityEntityTypeXref> getAll();

	EntityEntityTypeXref get(String id);

	EntityEntityTypeXref save(EntityEntityTypeXref entityEntityTypeXref);

	void delete(String id);
}
