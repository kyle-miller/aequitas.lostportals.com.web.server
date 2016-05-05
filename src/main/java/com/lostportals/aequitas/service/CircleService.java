package com.lostportals.aequitas.service;

import java.util.List;

import com.lostportals.aequitas.web.admin.domain.Circle;

public interface CircleService {
	List<Circle> getAll();

	Circle get(String id);

	Circle save(Circle circle);
}
