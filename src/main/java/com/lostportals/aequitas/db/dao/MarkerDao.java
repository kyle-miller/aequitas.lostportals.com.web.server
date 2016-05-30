package com.lostportals.aequitas.db.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.lostportals.aequitas.db.domain.DbMarker;

public interface MarkerDao {
	List<DbMarker> getAll();

	DbMarker get(String id);

	boolean save(DbMarker dbMarker) throws IllegalAccessException, DataAccessException;

	void delete(String id);
}
