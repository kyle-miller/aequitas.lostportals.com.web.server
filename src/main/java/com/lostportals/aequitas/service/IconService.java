package com.lostportals.aequitas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lostportals.aequitas.db.dao.IconDao;
import com.lostportals.aequitas.db.domain.DbIcon;
import com.lostportals.aequitas.exception.InternalServerException;
import com.lostportals.aequitas.exception.NotFoundException;
import com.lostportals.aequitas.exception.UnprocessableEntityException;
import com.lostportals.aequitas.web.admin.domain.Icon;

@Service
public class IconService {

	@Autowired
	IconDao iconDao;

	public List<Icon> getAll() {
		List<DbIcon> dbIconList = iconDao.getAll();

		List<Icon> iconList = new ArrayList<>();

		if (!CollectionUtils.isEmpty(dbIconList)) {
			dbIconList.stream().map(Icon::new).forEach(iconList::add);
		}

		return iconList;
	}

	public Icon get(String id) {
		DbIcon dbIcon = iconDao.get(id);

		if (dbIcon == null) {
			throw new NotFoundException("Cannot find Icon for id=" + id);
		}

		Icon icon = new Icon(dbIcon);

		return icon;
	}

	public Icon save(Icon iconToSave) {
		DbIcon dbIcon = new DbIcon(iconToSave);

		if (StringUtils.isBlank(dbIcon.getId())) {
			dbIcon.setId(UUID.randomUUID().toString());
		}

		try {
			iconDao.save(dbIcon);
		} catch (DataAccessException | IllegalAccessException e) {
			throw new InternalServerException("Unable to save icon=" + iconToSave, e);
		}

		Icon savedIcon = new Icon(dbIcon);
		return savedIcon;
	}

	public void delete(String id) { // TODO Test
		if (id == null) {
			throw new UnprocessableEntityException("id is required");
		} else if (get(id) != null) {
			iconDao.delete(id);	
		}
	}
}
