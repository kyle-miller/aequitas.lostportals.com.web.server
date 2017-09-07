package com.lostportals.aequitas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lostportals.aequitas.db.dao.MarkerDao;
import com.lostportals.aequitas.db.domain.DbMarker;
import com.lostportals.aequitas.exception.InternalServerException;
import com.lostportals.aequitas.exception.NotFoundException;
import com.lostportals.aequitas.exception.UnprocessableEntityException;
import com.lostportals.aequitas.web.admin.domain.Marker;

@Service
public class MarkerService {

	@Autowired
	MarkerDao markerDao;

	public List<Marker> getAll() {
		List<DbMarker> dbMarkerList = markerDao.getAll();

		List<Marker> markerList = new ArrayList<>();

		if (!CollectionUtils.isEmpty(dbMarkerList)) {
			dbMarkerList.stream().map(Marker::new).forEach(markerList::add);
		}

		return markerList;
	}

	public Marker get(String id) {
		DbMarker dbMarker = markerDao.get(id);

		if (dbMarker == null) {
			throw new NotFoundException("Cannot find Marker for id=" + id);
		}

		Marker marker = new Marker(dbMarker);

		return marker;
	}

	public Marker save(Marker markerToSave) {
		DbMarker dbMarker = new DbMarker(markerToSave);

		if (StringUtils.isBlank(dbMarker.getId())) {
			dbMarker.setId(UUID.randomUUID().toString());
		}

		try {
			markerDao.save(dbMarker);
		} catch (DataAccessException | IllegalAccessException e) {
			throw new InternalServerException("Unable to save marker=" + markerToSave, e);
		}

		Marker savedMarker = new Marker(dbMarker);
		return savedMarker;
	}

	public void delete(String id) { // TODO Test
		if (id == null) {
			throw new UnprocessableEntityException("id is required");
		} else if (get(id) != null) {
			markerDao.delete(id);	
		}
	}
}
