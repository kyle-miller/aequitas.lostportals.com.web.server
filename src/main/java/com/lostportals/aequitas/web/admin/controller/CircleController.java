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

import com.lostportals.aequitas.service.CircleService;
import com.lostportals.aequitas.web.admin.domain.Circle;

@RestController
@RequestMapping(value = "/api/admin/circles", produces = { "application/json" })
public class CircleController {

	private final CircleService circleService;

	@Autowired
	public CircleController(CircleService circleService) {
		this.circleService = circleService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> post(HttpServletRequest request, @RequestBody Circle circle) {
		circle = circleService.save(circle);

		URI newEntityUrl = URI.create(request.getRequestURI().replaceFirst("^(.*)/?$", "$1/" + circle.getId()));

		return ResponseEntity.created(newEntityUrl).build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Circle> getAll() {
		return circleService.getAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Circle get(@PathVariable String id) {
		return circleService.get(id);
	}
}
