package com.lostportals.aequitas.db.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.lostportals.aequitas.db.domain.DbIcon;

public interface IconDao {
	List<DbIcon> getAll();

	DbIcon get(String id);

	boolean save(DbIcon dbIcon) throws IllegalAccessException, DataAccessException;

	void delete(String id);
}
