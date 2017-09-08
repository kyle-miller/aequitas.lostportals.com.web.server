package com.lostportals.aequitas.db.dao;

import com.lostportals.aequitas.db.domain.DbPolygon;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PolygonDao extends Dao<DbPolygon> {

	public static final String TABLE_NAME = "polygons";

	@Override
	RowMapper<DbPolygon> getRowMapper() {
		return (rs, rowNum) -> mapFieldsTo(rs, new DbPolygon());
	}

	@Override
	String getTableName() {
		return TABLE_NAME;
	}
}
