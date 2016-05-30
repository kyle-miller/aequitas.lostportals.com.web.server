package com.lostportals.aequitas.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lostportals.aequitas.db.domain.DbMarker;

@Repository
public class MarkerDaoImpl extends Dao<DbMarker> implements MarkerDao {
	@Override
	RowMapper<DbMarker> getRowMapper() {
		return new RowMapper<DbMarker>() {
			@Override
			public DbMarker mapRow(ResultSet rs, int rowNum) throws SQLException {
				return mapFieldsTo(rs, new DbMarker());
			}
		};
	}

	@Override
	String getTableName() {
		return "markers";
	}

	@Override
	public List<DbMarker> getAll() {
		return super.getAll();
	}

	@Override
	public DbMarker get(String id) {
		return super.get(id);
	}

	@Override
	public boolean save(DbMarker dbMarker) throws IllegalAccessException, DataAccessException {
		return super.save(dbMarker);
	}

	@Override
	public void delete(String id) { // TODO Test
		super.delete(id);
	}
}
