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

import com.lostportals.aequitas.service.ImageService;
import com.lostportals.aequitas.web.admin.domain.Image;

@RestController
@RequestMapping(value = "/api/images", produces = { "application/json" })
public class ImageController {

	@Autowired
	ImageService imageService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> post(HttpServletRequest request, @RequestBody Image image) {
		image = imageService.save(image);

		URI newEntityUrl = URI.create(request.getRequestURI().replaceFirst("^(.*)/?$", "$1/" + image.getId()));

		return ResponseEntity.created(newEntityUrl).build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Image> getAll() {
		List<Image> imageList = imageService.getAll();
		return imageList;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Image get(@PathVariable String id) {
		Image image = imageService.get(id);
		return image;
	}
}
