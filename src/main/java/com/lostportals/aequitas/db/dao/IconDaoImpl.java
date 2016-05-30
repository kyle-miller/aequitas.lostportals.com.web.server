package com.lostportals.aequitas.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lostportals.aequitas.db.domain.DbIcon;

@Repository
public class IconDaoImpl extends Dao<DbIcon> implements IconDao {
	@Override
	RowMapper<DbIcon> getRowMapper() {
		return new RowMapper<DbIcon>() {
			@Override
			public DbIcon mapRow(ResultSet rs, int rowNum) throws SQLException {
				return mapFieldsTo(rs, new DbIcon());
			}
		};
	}

	@Override
	String getTableName() {
		return "icons";
	}

	@Override
	public List<DbIcon> getAll() {
		return super.getAll();
	}

	@Override
	public DbIcon get(String id) {
		return super.get(id);
	}

	@Override
	public boolean save(DbIcon dbIcon) throws IllegalAccessException, DataAccessException {
		return super.save(dbIcon);
	}

	@Override
	public void delete(String id) { // TODO Test
		super.delete(id);
	}
}
