package com.lostportals.aequitas.web.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lostportals.aequitas.service.MapEntityService;
import com.lostportals.aequitas.web.domain.MapEntity;

@RestController
@RequestMapping(value = "/api/mapEntities", produces = { "application/json" })
@CrossOrigin(allowedHeaders = { "*" })
public class MapEntityController {

	@Autowired
	MapEntityService mapEntityService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> post(HttpServletRequest request, @RequestBody MapEntity mapEntity) {
		mapEntity = mapEntityService.save(mapEntity);

		URI newUrl = URI.create(request.getRequestURI().replaceFirst("^(.*)/?$", "$1/" + mapEntity.getId()));

		return ResponseEntity.created(newUrl).build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<MapEntity> getAll() {
		List<MapEntity> mapEntityList = mapEntityService.getAll();
		return mapEntityList;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id) {
		mapEntityService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
