package com.lostportals.aequitas.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lostportals.aequitas.service.MapEntityTypeService;
import com.lostportals.aequitas.web.domain.MapEntityType;

@RestController
@RequestMapping(value = "/api/mapEntityTypes", produces = { "application/json" })
@CrossOrigin(allowedHeaders = {"*"})
public class MapEntityTypeController {

	private final MapEntityTypeService mapEntityTypeService;

	@Autowired
	public MapEntityTypeController(MapEntityTypeService mapEntityTypeService) {
		this.mapEntityTypeService = mapEntityTypeService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<MapEntityType> getAll() {
		return mapEntityTypeService.getAll();
	}
}
