package com.lostportals.aequitas.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lostportals.aequitas.db.domain.DbMarker;

@Repository
public class MarkerDao extends Dao<DbMarker> {

	public static final String TABLE_NAME = "markers";

	@Override
	RowMapper<DbMarker> getRowMapper() {
		return (rs, rowNum) -> mapFieldsTo(rs, new DbMarker());
	}

	@Override
	String getTableName() {
		return TABLE_NAME;
	}
}
