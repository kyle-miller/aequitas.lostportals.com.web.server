package com.lostportals.aequitas.db.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.lostportals.aequitas.db.domain.DbPolygon;

public interface PolygonDao {
	List<DbPolygon> getAll();

	DbPolygon get(String id);

	boolean save(DbPolygon dbPolygon) throws IllegalAccessException, DataAccessException;
}
