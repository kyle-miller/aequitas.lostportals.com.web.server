package com.lostportals.aequitas.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lostportals.aequitas.db.domain.DbEntityEntityTypeXref;

@Repository
public class EntityEntityTypeXrefDaoImpl extends Dao<DbEntityEntityTypeXref> implements EntityEntityTypeXrefDao {
	@Override
	RowMapper<DbEntityEntityTypeXref> getRowMapper() {
		return new RowMapper<DbEntityEntityTypeXref>() {
			@Override
			public DbEntityEntityTypeXref mapRow(ResultSet rs, int rowNum) throws SQLException {
				return mapFieldsTo(rs, new DbEntityEntityTypeXref());
			}
		};
	}

	@Override
	String getTableName() {
		return "entityEntityTypeXrefs";
	}

	@Override
	public List<DbEntityEntityTypeXref> getAll() {
		return super.getAll();
	}

	@Override
	public DbEntityEntityTypeXref get(String id) {
		return super.get(id);
	}

	@Override
	public boolean save(DbEntityEntityTypeXref dbEntityEntityTypeXref) throws IllegalAccessException, DataAccessException {
		return super.save(dbEntityEntityTypeXref);
	}
}
