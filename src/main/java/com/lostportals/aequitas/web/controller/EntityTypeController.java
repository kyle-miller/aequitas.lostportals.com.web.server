package com.lostportals.aequitas.web.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lostportals.aequitas.service.EntityTypeService;
import com.lostportals.aequitas.web.domain.EntityType;

@RestController
@RequestMapping(value = "/api/entityTypes", produces = {"application/json"})
public class EntityTypeController {
	
	@Autowired
	EntityTypeService entityTypeService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> post(HttpServletRequest request, @RequestBody EntityType entityType) {
		entityType = entityTypeService.save(entityType);
		
		URI newEntityUrl = URI.create(request.getRequestURI().replaceFirst("(.*)/?", "$1/" + entityType.getTypeCode()));
		
		return ResponseEntity.created(newEntityUrl).build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<EntityType> getAll() {
		List<EntityType> entityTypeList = entityTypeService.getAll();
		return entityTypeList;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public EntityType get(@PathVariable String id) {
		EntityType entityType = entityTypeService.get(id);
		return entityType;
	}
}
