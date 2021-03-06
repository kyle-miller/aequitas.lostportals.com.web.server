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

import com.lostportals.aequitas.service.MarkerService;
import com.lostportals.aequitas.web.admin.domain.Marker;

@RestController
@RequestMapping(value = "/api/admin/markers", produces = { "application/json" })
public class MarkerController {

	private final MarkerService markerService;

	@Autowired
	public MarkerController(MarkerService markerService) {
		this.markerService = markerService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> post(HttpServletRequest request, @RequestBody Marker marker) {
		marker = markerService.save(marker);

		URI newEntityUrl = URI.create(request.getRequestURI().replaceFirst("^(.*)/?$", "$1/" + marker.getId()));

		return ResponseEntity.created(newEntityUrl).build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Marker> getAll() {
		return markerService.getAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Marker get(@PathVariable String id) {
		return markerService.get(id);
	}
}
