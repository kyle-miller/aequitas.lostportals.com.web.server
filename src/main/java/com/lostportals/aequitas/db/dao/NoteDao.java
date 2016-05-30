package com.lostportals.aequitas.db.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.lostportals.aequitas.db.domain.DbNote;

public interface NoteDao {
	List<DbNote> getAll();

	DbNote get(String id);

	boolean save(DbNote dbNote) throws IllegalAccessException, DataAccessException;

	void delete(String id);
}
