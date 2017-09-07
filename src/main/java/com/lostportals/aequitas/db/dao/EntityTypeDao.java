package com.lostportals.aequitas.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lostportals.aequitas.db.domain.DbEntityType;

@Repository
public class EntityTypeDao extends Dao<DbEntityType> {
	@Override
	RowMapper<DbEntityType> getRowMapper() {
		return (rs, rowNum) -> mapFieldsTo(rs, new DbEntityType());
	}

	@Override
	String getTableName() {
		return "entityTypes";
	}

	@Override
	public List<DbEntityType> getAll() {
		return super.getAll();
	}

	@Override
	public DbEntityType get(String id) {
		return super.get(id);
	}

	@Override
	public boolean save(DbEntityType dbEntityType) throws IllegalAccessException, DataAccessException {
		return super.save(dbEntityType);
	}

	@Override
	public void delete(String id) { // TODO Test
		super.delete(id);
	}
}
