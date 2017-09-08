package com.lostportals.aequitas.db.dao;

import com.lostportals.aequitas.db.domain.DbNote;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoteDao extends Dao<DbNote> {

	public static final String TABLE_NAME = "notes";

	@Override
	RowMapper<DbNote> getRowMapper() {
		return (rs, rowNum) -> mapFieldsTo(rs, new DbNote());
	}

	@Override
	String getTableName() {
		return TABLE_NAME;
	}
}
