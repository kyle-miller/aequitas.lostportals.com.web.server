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

import com.lostportals.aequitas.db.domain.SqlType;

public abstract class Dao<T extends SqlType> extends JdbcDaoSupport {
	abstract String getTableName();

	abstract RowMapper<T> getRowMapper();

	@Autowired
	public void init(DataSource dataSource) {
		setDataSource(dataSource);
	}

	T mapFieldsTo(ResultSet rs, T dbObj) throws SQLException {
		Field[] fields = dbObj.getClass().getDeclaredFields();
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
				field.set(dbObj, obj);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new SQLException("Error setting field=" + field.getName(), e);
			}

			field.setAccessible(false);
		}

		return dbObj;
	}

	List<T> getAll() {
		String sql = "select * from " + getTableName();
		List<T> objList = getJdbcTemplate().query(sql, getRowMapper());
		return objList;
	}

	T get(String id) {
		String sql = "select * from " + getTableName() + " where id='" + id + "'";

		T obj = null;
		try {
			obj = getJdbcTemplate().queryForObject(sql, getRowMapper());
		} catch (EmptyResultDataAccessException e) {
			// ignore
		}

		return obj;
	}

	boolean save(T obj) throws IllegalAccessException, DataAccessException {
		String id = obj.getId();

		int numRowsAffected = 0;

		T existingObj = get(id);
		if (existingObj == null) {
			String sqlFields = obj.getInsertSqlFields();
			String sqlValues = obj.getInsertSqlValues();
			String sql = "insert into " + getTableName() + " (" + sqlFields + ") values (" + sqlValues + ")";
			numRowsAffected = getJdbcTemplate().update(sql);
		} else if (obj.toString().equals(existingObj.toString())) {
			return true;
		} else {
			String sqlFieldValuePairs = obj.getUpdateSqlSetFieldValuePairs();
			String sql = "update " + getTableName() + " set " + sqlFieldValuePairs + " where id='" + id + "'";
			numRowsAffected = getJdbcTemplate().update(sql);
		}

		if (numRowsAffected == 1) {
			return true;
		}

		return false;
	}
}
