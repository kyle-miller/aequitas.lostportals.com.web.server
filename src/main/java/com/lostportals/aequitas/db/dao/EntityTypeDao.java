package com.lostportals.aequitas.db.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.lostportals.aequitas.db.domain.DbEntityType;

public interface EntityTypeDao {
	List<DbEntityType> getAll();

	DbEntityType get(String id);

	boolean save(DbEntityType dbEntityType) throws IllegalAccessException, DataAccessException;

	void delete(String id);
}
