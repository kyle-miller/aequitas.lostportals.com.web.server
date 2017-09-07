package com.lostportals.aequitas.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lostportals.aequitas.web.domain.MapIcon;

@Service
public class MapIconService {

	@Autowired
	IconService entityTypeService;

	public List<MapIcon> getAll() {
		List<MapIcon> mapIconList = entityTypeService.getAll().stream().map(MapIcon::new).collect(Collectors.toList());
		return mapIconList;
	}
}
