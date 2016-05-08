package com.lostportals.aequitas.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lostportals.aequitas.web.domain.MapEntityType;

@Service
public class MapEntityTypeServiceImpl implements MapEntityTypeService {
	@Autowired
	EntityTypeService entityTypeService;

	@Override
	public List<MapEntityType> getAll() {
		List<MapEntityType> mapEntityTypeList = entityTypeService.getAll().stream().map(MapEntityType::new).collect(Collectors.toList());
		return mapEntityTypeList;
	}
}
