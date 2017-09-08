package com.lostportals.aequitas.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lostportals.aequitas.db.domain.DbImage;

@Repository
public class ImageDao extends Dao<DbImage> {

	public static final String TABLE_NAME = "images";

	@Override
	RowMapper<DbImage> getRowMapper() {
		return (rs, rowNum) -> mapFieldsTo(rs, new DbImage());
	}

	@Override
	String getTableName() {
		return TABLE_NAME;
	}
}
