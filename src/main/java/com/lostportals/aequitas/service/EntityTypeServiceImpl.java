package com.lostportals.aequitas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lostportals.aequitas.db.dao.EntityTypeDao;
import com.lostportals.aequitas.db.domain.DbEntityType;
import com.lostportals.aequitas.exception.InternalServerException;
import com.lostportals.aequitas.exception.NotFoundException;
import com.lostportals.aequitas.web.domain.EntityType;

@Service
public class EntityTypeServiceImpl implements EntityTypeService {
	static final Logger log = LogManager.getLogger();

	@Autowired
	EntityTypeDao entityTypeDao;

	@Override
	public List<EntityType> getAll() {
		List<DbEntityType> dbEntityTypeList = entityTypeDao.getAll();

		List<EntityType> entityTypeList = new ArrayList<>();

		if (!CollectionUtils.isEmpty(dbEntityTypeList)) {
			dbEntityTypeList.stream().map(EntityType::new).forEach(entityTypeList::add);
		}

		return entityTypeList;
	}

	@Override
	public EntityType get(String id) {
		DbEntityType dbEntityType = entityTypeDao.get(id);

		if (dbEntityType == null) {
			throw new NotFoundException("Cannot find EntityType for id=" + id);
		}

		EntityType entityType = new EntityType(dbEntityType);

		return entityType;
	}

	@Override
	public EntityType save(EntityType entityTypeToSave) {
		DbEntityType dbEntityType = new DbEntityType(entityTypeToSave);

		if (StringUtils.isBlank(dbEntityType.getId())) {
			dbEntityType.setId(UUID.randomUUID().toString());
		} else {
			DbEntityType existingDbEntityType = entityTypeDao.get(dbEntityType.getId());
			if (existingDbEntityType != null) {
				log.warn("EntityType already exists as dbEntityType=" + existingDbEntityType + "; overwriting with entityType=" + dbEntityType);
			}
		}

		boolean successful = entityTypeDao.save(dbEntityType);

		if (!successful) {
			throw new InternalServerException("Unable to save entityType=" + entityTypeToSave);
		}

		EntityType savedEntityType = new EntityType(dbEntityType);
		return savedEntityType;
	}
}
