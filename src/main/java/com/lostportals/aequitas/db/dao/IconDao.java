package com.lostportals.aequitas.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lostportals.aequitas.db.domain.DbIcon;

@Repository
public class IconDao extends Dao<DbIcon> {

	public static final String TABLE_NAME = "icons";

	@Override
	RowMapper<DbIcon> getRowMapper() {
		return (rs, rowNum) -> mapFieldsTo(rs, new DbIcon());
	}

	@Override
	String getTableName() {
		return TABLE_NAME;
	}
}
