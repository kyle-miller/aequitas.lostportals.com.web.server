package com.lostportals.aequitas.db.dao;

import com.lostportals.aequitas.db.domain.DbNote;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoteDao extends Dao<DbNote> {
	@Override
	RowMapper<DbNote> getRowMapper() {
		return (rs, rowNum) -> mapFieldsTo(rs, new DbNote());
	}

	@Override
	String getTableName() {
		return "notes";
	}

	@Override
	public List<DbNote> getAll() {
		return super.getAll();
	}

	@Override
	public DbNote get(String id) {
		return super.get(id);
	}

	@Override
	public boolean save(DbNote dbNote) throws IllegalAccessException, DataAccessException {
		return super.save(dbNote);
	}

	@Override
	public void delete(String id) { // TODO Test
		super.delete(id);
	}
}
