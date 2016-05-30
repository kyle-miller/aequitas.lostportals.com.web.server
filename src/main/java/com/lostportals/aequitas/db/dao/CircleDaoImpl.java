package com.lostportals.aequitas.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lostportals.aequitas.db.domain.DbCircle;

@Repository
public class CircleDaoImpl extends Dao<DbCircle> implements CircleDao {
	@Override
	RowMapper<DbCircle> getRowMapper() {
		return new RowMapper<DbCircle>() {
			@Override
			public DbCircle mapRow(ResultSet rs, int rowNum) throws SQLException {
				return mapFieldsTo(rs, new DbCircle());
			}
		};
	}

	@Override
	String getTableName() {
		return "circles";
	}

	@Override
	public List<DbCircle> getAll() {
		return super.getAll();
	}

	@Override
	public DbCircle get(String id) {
		return super.get(id);
	}

	@Override
	public boolean save(DbCircle dbCircle) throws IllegalAccessException, DataAccessException {
		return super.save(dbCircle);
	}

	@Override
	public void delete(String id) { // TODO Test
		super.delete(id);
	}
}
