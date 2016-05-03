package com.lostportals.aequitas.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
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
			dbEntityType.setId(rs.getString("id"));
			dbEntityType.setParentId(rs.getString("parentId"));
			dbEntityType.setName(rs.getString("name"));
			dbEntityType.setShow(rs.getBoolean("show"));
			return dbEntityType;
		}
	};

	@Autowired
	public void init(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public List<DbEntityType> getAll() {
		String sql = "select * from dbEntityTypes";
		List<DbEntityType> dbEntityTypeList = getJdbcTemplate().query(sql, rowMapper);
		return dbEntityTypeList;
	}

	@Override
	public DbEntityType get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(DbEntityType dbEntityType) {
		// TODO Auto-generated method stub
		return false;
	}
}
