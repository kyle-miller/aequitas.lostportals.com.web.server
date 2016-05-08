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

import com.lostportals.aequitas.service.EntityService;
import com.lostportals.aequitas.web.admin.domain.Entity;

@RestController
@RequestMapping(value = "/api/admin/entities", produces = { "application/json" })
public class EntityController {

	@Autowired
	EntityService entityService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> post(HttpServletRequest request, @RequestBody Entity entity) {
		entity = entityService.save(entity);

		URI newUrl = URI.create(request.getRequestURI().replaceFirst("^(.*)/?$", "$1/" + entity.getId()));

		return ResponseEntity.created(newUrl).build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Entity> getAll() {
		List<Entity> entityList = entityService.getAll();
		return entityList;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Entity get(@PathVariable String id) {
		Entity entity = entityService.get(id);
		return entity;
	}
}
