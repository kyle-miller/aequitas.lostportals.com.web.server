package com.lostportals.aequitas.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lostportals.aequitas.db.domain.DbEntityType;

@RestController
@RequestMapping(value = "/api/entityTypes", produces = {"application/json"})
public class EntityTypeController {

	@RequestMapping(method = RequestMethod.GET)
	public List<DbEntityType> getAll() {
		return null; // TODO
	}
}
