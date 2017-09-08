package com.lostportals.aequitas.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lostportals.aequitas.db.domain.DbCircle;

@Repository
public class CircleDao extends Dao<DbCircle> {

	public static final String TABLE_NAME = "circles";

	@Override
	RowMapper<DbCircle> getRowMapper() {
		return (rs, rowNum) -> mapFieldsTo(rs, new DbCircle());
	}

	@Override
	String getTableName() {
		return TABLE_NAME;
	}
}
