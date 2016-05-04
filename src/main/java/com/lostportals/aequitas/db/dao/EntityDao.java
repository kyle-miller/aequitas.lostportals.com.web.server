package com.lostportals.aequitas.db.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.lostportals.aequitas.db.domain.DbEntity;

public interface EntityDao {
	List<DbEntity> getAll();

	DbEntity get(String id);

	boolean save(DbEntity dbEntity) throws IllegalAccessException, DataAccessException;
}
