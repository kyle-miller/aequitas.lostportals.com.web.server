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

import com.lostportals.aequitas.service.IconService;
import com.lostportals.aequitas.web.admin.domain.Icon;

@RestController
@RequestMapping(value = "/api/admin/icons", produces = { "application/json" })
public class IconController {

	private final IconService iconService;

	@Autowired
	public IconController(IconService iconService) {
		this.iconService = iconService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> post(HttpServletRequest request, @RequestBody Icon icon) {
		icon = iconService.save(icon);

		URI newEntityUrl = URI.create(request.getRequestURI().replaceFirst("^(.*)/?$", "$1/" + icon.getId()));

		return ResponseEntity.created(newEntityUrl).build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Icon> getAll() {
		return iconService.getAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Icon get(@PathVariable String id) {
		return iconService.get(id);
	}
}
