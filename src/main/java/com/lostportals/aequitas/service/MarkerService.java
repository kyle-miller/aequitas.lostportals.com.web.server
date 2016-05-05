package com.lostportals.aequitas.service;

import java.util.List;

import com.lostportals.aequitas.web.admin.domain.Marker;

public interface MarkerService {
	List<Marker> getAll();

	Marker get(String id);

	Marker save(Marker marker);
}
