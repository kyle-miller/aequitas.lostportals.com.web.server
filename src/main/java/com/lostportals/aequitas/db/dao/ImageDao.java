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
	@Override
	RowMapper<DbImage> getRowMapper() {
		return (rs, rowNum) -> mapFieldsTo(rs, new DbImage());
	}

	@Override
	String getTableName() {
		return "images";
	}

	@Override
	public List<DbImage> getAll() {
		return super.getAll();
	}

	@Override
	public DbImage get(String id) {
		return super.get(id);
	}

	@Override
	public boolean save(DbImage dbImage) throws IllegalAccessException, DataAccessException {
		return super.save(dbImage);
	}

	@Override
	public void delete(String id) { // TODO Test
		super.delete(id);
	}
}
