package com.lostportals.aequitas.service;

import java.util.List;

import com.lostportals.aequitas.web.admin.domain.Polygon;

public interface PolygonService {
	List<Polygon> getAll();

	Polygon get(String id);

	Polygon save(Polygon polygon);

	void delete(String id);
}
