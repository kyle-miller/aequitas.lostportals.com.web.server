package com.lostportals.aequitas.web.admin.controller;

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
import com.lostportals.aequitas.web.admin.domain.EntityType;

@RestController
@RequestMapping(value = "/api/admin/entityTypes", produces = { "application/json" })
public class EntityTypeController {

	private final EntityTypeService entityTypeService;

	@Autowired
	public EntityTypeController(EntityTypeService entityTypeService) {
		this.entityTypeService = entityTypeService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> post(HttpServletRequest request, @RequestBody EntityType entityType) {
		entityType = entityTypeService.save(entityType);

		URI newEntityUrl = URI.create(request.getRequestURI().replaceFirst("^(.*)/?$", "$1/" + entityType.getId()));

		return ResponseEntity.created(newEntityUrl).build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<EntityType> getAll() {
		return entityTypeService.getAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public EntityType get(@PathVariable String id) {
		return entityTypeService.get(id);
	}
}
