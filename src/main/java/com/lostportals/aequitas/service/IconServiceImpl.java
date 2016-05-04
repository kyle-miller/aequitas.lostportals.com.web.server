package com.lostportals.aequitas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lostportals.aequitas.db.dao.IconDao;
import com.lostportals.aequitas.db.domain.DbIcon;
import com.lostportals.aequitas.exception.InternalServerException;
import com.lostportals.aequitas.exception.NotFoundException;
import com.lostportals.aequitas.web.domain.Icon;

@Service
public class IconServiceImpl implements IconService {
	static final Logger log = LogManager.getLogger();

	@Autowired
	IconDao iconDao;

	@Override
	public List<Icon> getAll() {
		List<DbIcon> dbIconList = iconDao.getAll();

		List<Icon> iconList = new ArrayList<>();

		if (!CollectionUtils.isEmpty(dbIconList)) {
			dbIconList.stream().map(Icon::new).forEach(iconList::add);
		}

		return iconList;
	}

	@Override
	public Icon get(String id) {
		DbIcon dbIcon = iconDao.get(id);

		if (dbIcon == null) {
			throw new NotFoundException("Cannot find Icon for id=" + id);
		}

		Icon icon = new Icon(dbIcon);

		return icon;
	}

	@Override
	public Icon save(Icon iconToSave) {
		DbIcon dbIcon = new DbIcon(iconToSave);

		if (StringUtils.isBlank(dbIcon.getId())) {
			dbIcon.setId(UUID.randomUUID().toString());
		} else {
			DbIcon existingDbIcon = iconDao.get(dbIcon.getId());
			if (existingDbIcon != null) {
				log.warn("Icon already exists as dbIcon=" + existingDbIcon + "; overwriting with icon=" + dbIcon);
			}
		}

		boolean successful = false;

		try {
			successful = iconDao.save(dbIcon);
		} catch (DataAccessException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!successful) {
			throw new InternalServerException("Unable to save icon=" + iconToSave);
		}

		Icon savedIcon = new Icon(dbIcon);
		return savedIcon;
	}
}
