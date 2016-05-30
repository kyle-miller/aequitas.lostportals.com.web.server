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
public class ImageServiceImpl implements ImageService {

	@Autowired
	ImageDao imageDao;

	@Override
	public List<Image> getAll() {
		List<DbImage> dbImageList = imageDao.getAll();

		List<Image> imageList = new ArrayList<>();

		if (!CollectionUtils.isEmpty(dbImageList)) {
			dbImageList.stream().map(Image::new).forEach(imageList::add);
		}

		return imageList;
	}

	@Override
	public Image get(String id) {
		DbImage dbImage = imageDao.get(id);

		if (dbImage == null) {
			throw new NotFoundException("Cannot find Image for id=" + id);
		}

		Image image = new Image(dbImage);

		return image;
	}

	@Override
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

		Image savedImage = new Image(dbImage);
		return savedImage;
	}

	@Override
	public void delete(String id) { // TODO Test
		if (id == null) {
			throw new UnprocessableEntityException("id is required");
		} else if (get(id) != null) {
			imageDao.delete(id);	
		}
	}
}
