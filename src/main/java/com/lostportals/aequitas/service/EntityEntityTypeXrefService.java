package com.lostportals.aequitas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lostportals.aequitas.db.dao.EntityEntityTypeXrefDao;
import com.lostportals.aequitas.db.domain.DbEntityEntityTypeXref;
import com.lostportals.aequitas.exception.InternalServerException;
import com.lostportals.aequitas.exception.NotFoundException;
import com.lostportals.aequitas.exception.UnprocessableEntityException;
import com.lostportals.aequitas.web.admin.domain.EntityEntityTypeXref;

@Service
public class EntityEntityTypeXrefService {

	private final EntityEntityTypeXrefDao entityEntityTypeXrefDao;

	@Autowired
	public EntityEntityTypeXrefService(EntityEntityTypeXrefDao entityEntityTypeXrefDao) {
		this.entityEntityTypeXrefDao = entityEntityTypeXrefDao;
	}

	public List<EntityEntityTypeXref> getAll() {
		List<DbEntityEntityTypeXref> dbEntityEntityTypeXrefList = entityEntityTypeXrefDao.getAll();

		List<EntityEntityTypeXref> entityEntityTypeXrefList = new ArrayList<>();

		if (!CollectionUtils.isEmpty(dbEntityEntityTypeXrefList)) {
			dbEntityEntityTypeXrefList.stream().map(EntityEntityTypeXref::new).forEach(entityEntityTypeXrefList::add);
		}

		return entityEntityTypeXrefList;
	}

	public EntityEntityTypeXref get(String id) {
		DbEntityEntityTypeXref dbEntityEntityTypeXref = entityEntityTypeXrefDao.get(id);

		if (dbEntityEntityTypeXref == null) {
			throw new NotFoundException("Cannot find EntityEntityTypeXref for id=" + id);
		}

		return new EntityEntityTypeXref(dbEntityEntityTypeXref);
	}

	public EntityEntityTypeXref save(EntityEntityTypeXref entityEntityTypeXrefToSave) {
		DbEntityEntityTypeXref dbEntityEntityTypeXref = new DbEntityEntityTypeXref(entityEntityTypeXrefToSave);

		if (StringUtils.isBlank(dbEntityEntityTypeXref.getId())) {
			dbEntityEntityTypeXref.setId(UUID.randomUUID().toString());
		}

		try {
			entityEntityTypeXrefDao.save(dbEntityEntityTypeXref);
		} catch (DataAccessException | IllegalAccessException e) {
			throw new InternalServerException("Unable to save entityEntityTypeXref=" + entityEntityTypeXrefToSave, e);
		}

		return new EntityEntityTypeXref(dbEntityEntityTypeXref);
	}

	public void delete(String id) { // TODO Test
		if (id == null) {
			throw new UnprocessableEntityException("id is required");
		} else if (get(id) != null) {
			entityEntityTypeXrefDao.delete(id);	
		}
	}
}
