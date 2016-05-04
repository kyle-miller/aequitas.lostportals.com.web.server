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

import com.lostportals.aequitas.service.IconService;
import com.lostportals.aequitas.web.domain.Icon;

@RestController
@RequestMapping(value = "/api/icons", produces = { "application/json" })
public class IconController {

	@Autowired
	IconService iconService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> post(HttpServletRequest request, @RequestBody Icon icon) {
		icon = iconService.save(icon);

		URI newEntityUrl = URI.create(request.getRequestURI().replaceFirst("^(.*)/?$", "$1/" + icon.getId()));

		return ResponseEntity.created(newEntityUrl).build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Icon> getAll() {
		List<Icon> iconList = iconService.getAll();
		return iconList;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Icon get(@PathVariable String id) {
		Icon icon = iconService.get(id);
		return icon;
	}
}
