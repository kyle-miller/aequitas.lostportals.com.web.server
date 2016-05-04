package com.lostportals.aequitas.db.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.lostportals.aequitas.db.domain.DbEntityEntityTypeXref;

public interface EntityEntityTypeXrefDao {
	List<DbEntityEntityTypeXref> getAll();

	DbEntityEntityTypeXref get(String id);

	boolean save(DbEntityEntityTypeXref dbEntityEntityTypeXref) throws IllegalAccessException, DataAccessException;
}
