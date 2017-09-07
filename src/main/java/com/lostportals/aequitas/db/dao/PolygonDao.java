package com.lostportals.aequitas.db.dao;

import com.lostportals.aequitas.db.domain.DbPolygon;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PolygonDao extends Dao<DbPolygon> {
	@Override
	RowMapper<DbPolygon> getRowMapper() {
		return (rs, rowNum) -> mapFieldsTo(rs, new DbPolygon());
	}

	@Override
	String getTableName() {
		return "polygons";
	}

	@Override
	public List<DbPolygon> getAll() {
		return super.getAll();
	}

	@Override
	public DbPolygon get(String id) {
		return super.get(id);
	}

	@Override
	public boolean save(DbPolygon dbPolygon) throws IllegalAccessException, DataAccessException {
		return super.save(dbPolygon);
	}

	@Override
	public void delete(String id) { // TODO Test
		super.delete(id);
	}
}
