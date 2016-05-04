package com.lostportals.aequitas.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lostportals.aequitas.db.domain.DbNote;

@Repository
public class NoteDaoImpl extends Dao<DbNote> implements NoteDao {
	@Override
	RowMapper<DbNote> getRowMapper() {
		return new RowMapper<DbNote>() {
			@Override
			public DbNote mapRow(ResultSet rs, int rowNum) throws SQLException {
				return mapFieldsTo(rs, new DbNote());
			}
		};
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
}
