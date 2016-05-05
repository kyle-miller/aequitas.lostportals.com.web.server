package com.lostportals.aequitas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lostportals.aequitas.db.dao.EntityDao;
import com.lostportals.aequitas.db.domain.DbEntity;
import com.lostportals.aequitas.exception.InternalServerException;
import com.lostportals.aequitas.exception.NotFoundException;
import com.lostportals.aequitas.web.domain.Entity;

@Service
public class EntityServiceImpl implements EntityService {

	@Autowired
	EntityDao entityDao;

	@Override
	public List<Entity> getAll() {
		List<DbEntity> dbEntityList = entityDao.getAll();

		List<Entity> entityList = new ArrayList<>();

		if (!CollectionUtils.isEmpty(dbEntityList)) {
			dbEntityList.stream().map(Entity::new).forEach(entityList::add);
		}

		return entityList;
	}

	@Override
	public Entity get(String id) {
		DbEntity dbEntity = entityDao.get(id);

		if (dbEntity == null) {
			throw new NotFoundException("Cannot find Entity for id=" + id);
		}

		Entity entity = new Entity(dbEntity);

		return entity;
	}

	@Override
	public Entity save(Entity entityToSave) {
		DbEntity dbEntity = new DbEntity(entityToSave);

		if (StringUtils.isBlank(dbEntity.getId())) {
			dbEntity.setId(UUID.randomUUID().toString());
		}

		try {
			entityDao.save(dbEntity);
		} catch (DataAccessException | IllegalAccessException e) {
			throw new InternalServerException("Unable to save entity=" + entityToSave, e);
		}

		Entity savedEntity = new Entity(dbEntity);
		return savedEntity;
	}
}
