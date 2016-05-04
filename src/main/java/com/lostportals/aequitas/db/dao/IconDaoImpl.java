package com.lostportals.aequitas.db.dao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.lostportals.aequitas.db.domain.DbIcon;

@Repository
public class IconDaoImpl extends JdbcDaoSupport implements IconDao {
	static final RowMapper<DbIcon> rowMapper = new RowMapper<DbIcon>() {
		@Override
		public DbIcon mapRow(ResultSet rs, int rowNum) throws SQLException {
			DbIcon dbIcon = new DbIcon();

			Field[] fields = dbIcon.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				field.setAccessible(true);

				Object obj = rs.getObject(field.getName());

				if (obj instanceof String) {
					String str = (String) obj;
					if (StringUtils.isBlank(str) || "null".equals(str)) {
						obj = null;
					}
				}

				try {
					field.set(dbIcon, obj);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new SQLException("Error setting field=" + field.getName(), e);
				}

				field.setAccessible(false);
			}

			return dbIcon;
		}
	};

	@Autowired
	public void init(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public List<DbIcon> getAll() {
		String sql = "select * from icons";
		List<DbIcon> dbIconList = getJdbcTemplate().query(sql, rowMapper);
		return dbIconList;
	}

	@Override
	public DbIcon get(String id) {
		String sql = "select * from icons where id='" + id + "'";

		DbIcon dbIcon = null;
		try {
			dbIcon = getJdbcTemplate().queryForObject(sql, rowMapper);
		} catch (EmptyResultDataAccessException e) {
			// ignore
		}

		return dbIcon;
	}

	@Override
	public boolean save(DbIcon dbIcon) throws IllegalAccessException, DataAccessException {
		String id = dbIcon.getId();

		int numRowsAffected = 0;

		DbIcon existingDbIcon = get(id);
		if (existingDbIcon == null) {
			String sqlFields = dbIcon.getInsertSqlFields();
			String sqlValues = dbIcon.getInsertSqlValues();
			String sql = "insert into icons (" + sqlFields + ") values (" + sqlValues + ")";
			numRowsAffected = getJdbcTemplate().update(sql);
		} else if (dbIcon.toString().equals(existingDbIcon.toString())) {
			return true;
		} else {
			String sqlFieldValuePairs = dbIcon.getUpdateSqlSetFieldValuePairs();
			String sql = "update icons set " + sqlFieldValuePairs + " where id='" + id + "'";
			numRowsAffected = getJdbcTemplate().update(sql);
		}

		if (numRowsAffected == 1) {
			return true;
		}

		return false;
	}
}
