package com.lostportals.aequitas.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lostportals.aequitas.db.domain.DbEntityEntityTypeXref;

@Repository
public class EntityEntityTypeXrefDao extends Dao<DbEntityEntityTypeXref> {

	public static final String TABLE_NAME = "entityEntityTypeXrefs";

	@Override
	RowMapper<DbEntityEntityTypeXref> getRowMapper() {
		return (rs, rowNum) -> mapFieldsTo(rs, new DbEntityEntityTypeXref());
	}

	@Override
	String getTableName() {
		return TABLE_NAME;
	}
}
