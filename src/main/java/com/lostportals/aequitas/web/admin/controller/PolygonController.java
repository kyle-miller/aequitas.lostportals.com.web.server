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

import com.lostportals.aequitas.service.PolygonService;
import com.lostportals.aequitas.web.admin.domain.Polygon;

@RestController
@RequestMapping(value = "/api/admin/polygons", produces = { "application/json" })
public class PolygonController {

	private final PolygonService polygonService;

	@Autowired
	public PolygonController(PolygonService polygonService) {
		this.polygonService = polygonService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> post(HttpServletRequest request, @RequestBody Polygon polygon) {
		polygon = polygonService.save(polygon);

		URI newEntityUrl = URI.create(request.getRequestURI().replaceFirst("^(.*)/?$", "$1/" + polygon.getId()));

		return ResponseEntity.created(newEntityUrl).build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Polygon> getAll() {
		return polygonService.getAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Polygon get(@PathVariable String id) {
		return polygonService.get(id);
	}
}
