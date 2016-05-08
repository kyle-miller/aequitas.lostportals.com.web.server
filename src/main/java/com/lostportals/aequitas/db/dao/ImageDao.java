package com.lostportals.aequitas.db.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.lostportals.aequitas.db.domain.DbImage;

public interface ImageDao {
	List<DbImage> getAll();

	DbImage get(String id);

	boolean save(DbImage dbImage) throws IllegalAccessException, DataAccessException;
}
