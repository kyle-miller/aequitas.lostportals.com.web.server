package com.lostportals.aequitas.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lostportals.aequitas.service.MapIconService;
import com.lostportals.aequitas.web.domain.MapIcon;

@RestController
@RequestMapping(value = "/api/mapIcons", produces = { "application/json" })
@CrossOrigin(allowedHeaders = {"*"})
public class MapIconController {

	private final MapIconService mapIconService;

	@Autowired
	public MapIconController(MapIconService mapIconService) {
		this.mapIconService = mapIconService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<MapIcon> getAll() {
		return mapIconService.getAll();
	}
}
