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

	public static final String TABLE_NAME = "entityTypes";

	@Override
	RowMapper<DbEntityType> getRowMapper() {
		return (rs, rowNum) -> mapFieldsTo(rs, new DbEntityType());
	}

	@Override
	String getTableName() {
		return TABLE_NAME;
	}
}
