package com.lostportals.aequitas.service;

import java.util.List;

import com.lostportals.aequitas.web.domain.Icon;

public interface IconService {
	List<Icon> getAll();

	Icon get(String id);

	Icon save(Icon icon);
}
