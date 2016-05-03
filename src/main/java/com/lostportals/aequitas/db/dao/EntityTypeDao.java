package com.lostportals.aequitas.db.dao;

import java.util.List;

import com.lostportals.aequitas.db.domain.DbEntityType;

public interface EntityTypeDao {
	List<DbEntityType> getAll();

	DbEntityType get(String id);

	boolean save(DbEntityType dbEntityType);
}
