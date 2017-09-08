package com.lostportals.aequitas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lostportals.aequitas.db.dao.PolygonDao;
import com.lostportals.aequitas.db.domain.DbPolygon;
import com.lostportals.aequitas.exception.InternalServerException;
import com.lostportals.aequitas.exception.NotFoundException;
import com.lostportals.aequitas.exception.UnprocessableEntityException;
import com.lostportals.aequitas.web.admin.domain.Polygon;

@Service
public class PolygonService {

	private final PolygonDao polygonDao;

	@Autowired
	public PolygonService(PolygonDao polygonDao) {
		this.polygonDao = polygonDao;
	}

	public List<Polygon> getAll() {
		List<DbPolygon> dbPolygonList = polygonDao.getAll();

		List<Polygon> polygonList = new ArrayList<>();

		if (!CollectionUtils.isEmpty(dbPolygonList)) {
			dbPolygonList.stream().map(Polygon::new).forEach(polygonList::add);
		}

		return polygonList;
	}

	public Polygon get(String id) {
		DbPolygon dbPolygon = polygonDao.get(id);

		if (dbPolygon == null) {
			throw new NotFoundException("Cannot find Polygon for id=" + id);
		}

		return new Polygon(dbPolygon);
	}

	public Polygon save(Polygon polygonToSave) {
		DbPolygon dbPolygon = new DbPolygon(polygonToSave);

		if (StringUtils.isBlank(dbPolygon.getId())) {
			dbPolygon.setId(UUID.randomUUID().toString());
		}

		try {
			polygonDao.save(dbPolygon);
		} catch (DataAccessException | IllegalAccessException e) {
			throw new InternalServerException("Unable to save polygon=" + polygonToSave, e);
		}

		return new Polygon(dbPolygon);
	}

	public void delete(String id) { // TODO Test
		if (id == null) {
			throw new UnprocessableEntityException("id is required");
		} else if (get(id) != null) {
			polygonDao.delete(id);	
		}
	}
}
