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

import com.lostportals.aequitas.service.EntityEntityTypeXrefService;
import com.lostportals.aequitas.web.admin.domain.EntityEntityTypeXref;

@RestController
@RequestMapping(value = "/api/admin/entityEntityTypeXrefs", produces = { "application/json" })
public class EntityEntityTypeXrefController {

	@Autowired
	EntityEntityTypeXrefService entityEntityTypeXrefService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> post(HttpServletRequest request, @RequestBody EntityEntityTypeXref entityEntityTypeXref) {
		entityEntityTypeXref = entityEntityTypeXrefService.save(entityEntityTypeXref);

		URI newEntityUrl = URI.create(request.getRequestURI().replaceFirst("^(.*)/?$", "$1/" + entityEntityTypeXref.getId()));

		return ResponseEntity.created(newEntityUrl).build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<EntityEntityTypeXref> getAll() {
		List<EntityEntityTypeXref> entityEntityTypeXrefList = entityEntityTypeXrefService.getAll();
		return entityEntityTypeXrefList;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public EntityEntityTypeXref get(@PathVariable String id) {
		EntityEntityTypeXref entityEntityTypeXref = entityEntityTypeXrefService.get(id);
		return entityEntityTypeXref;
	}
}
