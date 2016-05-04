package com.lostportals.aequitas.db.dao;

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

import com.lostportals.aequitas.db.domain.DbEntityType;

@Repository
public class EntityTypeDaoImpl extends JdbcDaoSupport implements EntityTypeDao {
	static final RowMapper<DbEntityType> rowMapper = new RowMapper<DbEntityType>() {
		@Override
		public DbEntityType mapRow(ResultSet rs, int rowNum) throws SQLException {
			DbEntityType dbEntityType = new DbEntityType();
			dbEntityType.setId(mapNull(rs.getString("id")));
			dbEntityType.setParentId(mapNull(rs.getString("parentId")));
			dbEntityType.setName(mapNull(rs.getString("name")));
			dbEntityType.setShow(rs.getBoolean("show"));
			return dbEntityType;
		}

		String mapNull(String str) {
			if (StringUtils.isBlank(str) || "null".equals(str)) {
				return null;
			}
			return str;
		}
	};

	@Autowired
	public void init(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public List<DbEntityType> getAll() {
		String sql = "select * from entityTypes";
		List<DbEntityType> dbEntityTypeList = getJdbcTemplate().query(sql, rowMapper);
		return dbEntityTypeList;
	}

	@Override
	public DbEntityType get(String id) {
		String sql = "select * from entityTypes where id='" + id + "'";

		DbEntityType dbEntityType = null;
		try {
			dbEntityType = getJdbcTemplate().queryForObject(sql, rowMapper);
		} catch (EmptyResultDataAccessException e) {
			// ignore
		}

		return dbEntityType;
	}

	@Override
	public boolean save(DbEntityType dbEntityType) throws IllegalAccessException, DataAccessException {
		String id = dbEntityType.getId();
		
		int numRowsAffected = 0;

		DbEntityType existingDbEntityType = get(id);
		if (existingDbEntityType == null) {
			String sqlFields = dbEntityType.getInsertSqlFields();
			String sqlValues = dbEntityType.getInsertSqlValues();
			String sql = "insert into entityTypes (" + sqlFields + ") values (" + sqlValues + ")";
			numRowsAffected = getJdbcTemplate().update(sql);
		} else if (dbEntityType.toString().equals(existingDbEntityType.toString())) {
			return true;
		} else {
			String sqlFieldValuePairs = dbEntityType.getUpdateSqlSetFieldValuePairs();
			String sql = "update entityTypes set " + sqlFieldValuePairs + " where id='" + id + "'";
			numRowsAffected = getJdbcTemplate().update(sql);
		}

		if (numRowsAffected == 1) {
			return true;
		}

		return false;
	}
}
