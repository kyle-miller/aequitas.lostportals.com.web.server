package com.lostportals.aequitas.service;

import java.util.List;

import com.lostportals.aequitas.web.domain.MapEntity;

public interface MapEntityService {
	List<MapEntity> getAll();

	MapEntity save(MapEntity mapEntity);
	
	void delete(String id);
}
