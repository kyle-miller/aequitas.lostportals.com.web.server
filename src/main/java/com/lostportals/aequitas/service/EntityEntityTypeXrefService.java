package com.lostportals.aequitas.service;

import java.util.List;

import com.lostportals.aequitas.web.db.domain.EntityEntityTypeXref;

public interface EntityEntityTypeXrefService {
	List<EntityEntityTypeXref> getAll();

	EntityEntityTypeXref get(String id);

	EntityEntityTypeXref save(EntityEntityTypeXref entityEntityTypeXref);
}
