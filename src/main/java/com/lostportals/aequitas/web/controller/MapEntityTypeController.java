package com.lostportals.aequitas.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lostportals.aequitas.service.MapEntityTypeService;
import com.lostportals.aequitas.web.domain.MapEntityType;

@RestController
@RequestMapping(value = "/api/mapEntityTypes", produces = { "application/json" })
public class MapEntityTypeController {

	@Autowired
	MapEntityTypeService mapEntityTypeService;

	@RequestMapping(method = RequestMethod.GET)
	public List<MapEntityType> getAll() {
		List<MapEntityType> mapEntityTypeList = mapEntityTypeService.getAll();
		return mapEntityTypeList;
	}
}
