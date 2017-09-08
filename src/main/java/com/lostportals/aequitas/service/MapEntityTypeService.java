package com.lostportals.aequitas.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lostportals.aequitas.web.domain.MapEntityType;

@Service
public class MapEntityTypeService {

	private final EntityTypeService entityTypeService;

	@Autowired
	public MapEntityTypeService(EntityTypeService entityTypeService) {
		this.entityTypeService = entityTypeService;
	}

	public List<MapEntityType> getAll() {
		return entityTypeService.getAll().stream().map(MapEntityType::new).collect(Collectors.toList());
	}
}
