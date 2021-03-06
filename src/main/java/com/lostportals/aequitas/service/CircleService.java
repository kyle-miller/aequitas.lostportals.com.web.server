package com.lostportals.aequitas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lostportals.aequitas.db.dao.CircleDao;
import com.lostportals.aequitas.db.domain.DbCircle;
import com.lostportals.aequitas.exception.InternalServerException;
import com.lostportals.aequitas.exception.NotFoundException;
import com.lostportals.aequitas.exception.UnprocessableEntityException;
import com.lostportals.aequitas.web.admin.domain.Circle;

@Service
public class CircleService {

	private final CircleDao circleDao;

	@Autowired
	public CircleService(CircleDao circleDao) {
		this.circleDao = circleDao;
	}

	public List<Circle> getAll() {
		List<DbCircle> dbCircleList = circleDao.getAll();

		List<Circle> circleList = new ArrayList<>();

		if (!CollectionUtils.isEmpty(dbCircleList)) {
			dbCircleList.stream().map(Circle::new).forEach(circleList::add);
		}

		return circleList;
	}

	public Circle get(String id) {
		DbCircle dbCircle = circleDao.get(id);

		if (dbCircle == null) {
			throw new NotFoundException("Cannot find Circle for id=" + id);
		}

		return new Circle(dbCircle);
	}

	public Circle save(Circle circleToSave) {
		DbCircle dbCircle = new DbCircle(circleToSave);

		if (StringUtils.isBlank(dbCircle.getId())) {
			dbCircle.setId(UUID.randomUUID().toString());
		}

		try {
			circleDao.save(dbCircle);
		} catch (DataAccessException | IllegalAccessException e) {
			throw new InternalServerException("Unable to save circle=" + circleToSave, e);
		}

		return new Circle(dbCircle);
	}

	public void delete(String id) {
		if (id == null) {
			throw new UnprocessableEntityException("id is required");
		} else if (get(id) != null) {
			circleDao.delete(id);	
		}
	}
}
