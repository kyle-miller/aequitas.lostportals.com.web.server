package com.lostportals.aequitas.db.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.lostportals.aequitas.db.domain.DbCircle;

public interface CircleDao {
	List<DbCircle> getAll();

	DbCircle get(String id);

	boolean save(DbCircle dbCircle) throws IllegalAccessException, DataAccessException;

	void delete(String id);
}
