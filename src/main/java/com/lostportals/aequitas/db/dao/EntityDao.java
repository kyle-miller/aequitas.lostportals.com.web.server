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

	public static final String TABLE_NAME = "entities";

	@Override
	RowMapper<DbEntity> getRowMapper() {
		return (rs, rowNum) -> mapFieldsTo(rs, new DbEntity());
	}

	@Override
	String getTableName() {
		return TABLE_NAME;
	}
}
