package com.lostportals.aequitas.db.dao;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.sql.ResultSet;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.lostportals.aequitas.db.domain.DbEntityType;

@RunWith(MockitoJUnitRunner.class)
public class EntityTypeDaoImpl_UT {

	@InjectMocks
	@Spy
	EntityTypeDaoImpl testObj;

	@Mock
	DataSource dataSource;
	
	@Mock
	JdbcTemplate jdbcTemplate;
	
	@Before
	public void before() {
		doReturn(jdbcTemplate).when(testObj).getJdbcTemplate();
		doReturn(dataSource).when(testObj).getDataSource();
	}
	
	@Test
	public void rowMapper() throws Exception {
		RowMapper<DbEntityType> rowMapper = testObj.rowMapper;
		ResultSet resultSet = mock(ResultSet.class);
		
		rowMapper.mapRow(resultSet, 0);
		
		
	}

//	@Test
//	public void getAll_testQuery() {
//		testObj.getAll();
//
//		verify(jdbcTemplate).query("", );
//	}
}
