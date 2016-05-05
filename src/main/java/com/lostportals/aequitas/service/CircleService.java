package com.lostportals.aequitas.service;

import java.util.List;

import com.lostportals.aequitas.web.db.domain.Circle;

public interface CircleService {
	List<Circle> getAll();

	Circle get(String id);

	Circle save(Circle circle);
}
