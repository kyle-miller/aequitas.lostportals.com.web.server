package com.lostportals.aequitas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lostportals.aequitas.db.dao.ImageDao;
import com.lostportals.aequitas.db.domain.DbImage;
import com.lostportals.aequitas.exception.InternalServerException;
import com.lostportals.aequitas.exception.NotFoundException;
import com.lostportals.aequitas.exception.UnprocessableEntityException;
import com.lostportals.aequitas.web.admin.domain.Image;

@Service
public class ImageService {

	private final ImageDao imageDao;

	@Autowired
	public ImageService(ImageDao imageDao) {
		this.imageDao = imageDao;
	}

	public List<Image> getAll() {
		List<DbImage> dbImageList = imageDao.getAll();

		List<Image> imageList = new ArrayList<>();

		if (!CollectionUtils.isEmpty(dbImageList)) {
			dbImageList.stream().map(Image::new).forEach(imageList::add);
		}

		return imageList;
	}

	public Image get(String id) {
		DbImage dbImage = imageDao.get(id);

		if (dbImage == null) {
			throw new NotFoundException("Cannot find Image for id=" + id);
		}

		return new Image(dbImage);
	}

	public Image save(Image imageToSave) {
		DbImage dbImage = new DbImage(imageToSave);

		if (StringUtils.isBlank(dbImage.getId())) {
			dbImage.setId(UUID.randomUUID().toString());
		}

		try {
			imageDao.save(dbImage);
		} catch (DataAccessException | IllegalAccessException e) {
			throw new InternalServerException("Unable to save image=" + imageToSave, e);
		}

		return new Image(dbImage);
	}

	public void delete(String id) { // TODO Test
		if (id == null) {
			throw new UnprocessableEntityException("id is required");
		} else if (get(id) != null) {
			imageDao.delete(id);	
		}
	}
}
