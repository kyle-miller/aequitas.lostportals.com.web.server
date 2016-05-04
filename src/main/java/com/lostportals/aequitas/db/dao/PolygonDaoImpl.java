package com.lostportals.aequitas.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lostportals.aequitas.db.domain.DbPolygon;

@Repository
public class PolygonDaoImpl extends Dao<DbPolygon> implements PolygonDao {
	@Override
	RowMapper<DbPolygon> getRowMapper() {
		return new RowMapper<DbPolygon>() {
			@Override
			public DbPolygon mapRow(ResultSet rs, int rowNum) throws SQLException {
				return mapFieldsTo(rs, new DbPolygon());
			}
		};
	}

	@Override
	String getTableName() {
		return "markers";
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
}
