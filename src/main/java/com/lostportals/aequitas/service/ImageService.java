package com.lostportals.aequitas.service;

import java.util.List;

import com.lostportals.aequitas.web.admin.domain.Image;

public interface ImageService {
	List<Image> getAll();

	Image get(String id);

	Image save(Image image);
}
