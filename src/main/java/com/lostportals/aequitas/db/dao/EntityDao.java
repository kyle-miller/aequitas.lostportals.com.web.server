package com.lostportals.aequitas.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lostportals.aequitas.db.domain.DbEntity;

@Repository
public class EntityDao extends Dao<DbEntity> {
	@Override
	RowMapper<DbEntity> getRowMapper() {
		return (rs, rowNum) -> mapFieldsTo(rs, new DbEntity());
	}

	@Override
	String getTableName() {
		return "entities";
	}

	@Override
	public List<DbEntity> getAll() {
		return super.getAll();
	}

	@Override
	public DbEntity get(String id) {
		return super.get(id);
	}

	@Override
	public boolean save(DbEntity dbEntity) throws IllegalAccessException, DataAccessException {
		return super.save(dbEntity);
	}

	@Override
	public void delete(String id) { // TODO Test
		super.delete(id);
	}
}
