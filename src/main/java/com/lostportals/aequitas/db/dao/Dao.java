package com.lostportals.aequitas.db.dao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.lostportals.aequitas.db.domain.SqlType;
import com.lostportals.aequitas.exception.InternalServerException;

public abstract class Dao<T extends SqlType> extends JdbcDaoSupport {
	abstract String getTableName();

	abstract RowMapper<T> getRowMapper();

	@Autowired
	public void init(DataSource dataSource) {
		setDataSource(dataSource);
	}

	T mapFieldsTo(ResultSet rs, T dbObj) throws SQLException {
		Field[] fields = dbObj.getClass().getDeclaredFields();
		for (Field field : fields) {
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

	public List<T> getAll() {
		String sql = "select * from " + getTableName();
		return getJdbcTemplate().query(sql, getRowMapper());
	}

	public T get(String id) {
		String sql = "select * from " + getTableName() + " where id='" + id + "'";

		T obj = null;
		try {
			obj = getJdbcTemplate().queryForObject(sql, getRowMapper());
		} catch (EmptyResultDataAccessException e) {
			// ignore
		}

		return obj;
	}

	public boolean save(T obj) throws IllegalAccessException, DataAccessException {
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

		return numRowsAffected == 1;

	}

	public void delete(String id) {
		String sql = "delete from " + getTableName() + " where id='" + id + "'";
		try {
			getJdbcTemplate().execute(sql);
		} catch (DataAccessException e) {
			throw new InternalServerException("Failed to delete id=" + id + " from " + getTableName(), e);
		}
	}
}
