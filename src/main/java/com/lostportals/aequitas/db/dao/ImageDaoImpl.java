package com.lostportals.aequitas.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lostportals.aequitas.db.domain.DbImage;

@Repository
public class ImageDaoImpl extends Dao<DbImage> implements ImageDao {
	@Override
	RowMapper<DbImage> getRowMapper() {
		return new RowMapper<DbImage>() {
			@Override
			public DbImage mapRow(ResultSet rs, int rowNum) throws SQLException {
				return mapFieldsTo(rs, new DbImage());
			}
		};
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
}
