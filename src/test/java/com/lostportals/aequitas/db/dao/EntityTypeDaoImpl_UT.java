package com.lostportals.aequitas.db.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

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
	static final RowMapper<DbEntityType> rowMapper = EntityTypeDaoImpl.rowMapper;

	@InjectMocks
	@Spy
	EntityTypeDaoImpl testObj;

	@Mock
	DataSource dataSource;

	@Mock
	JdbcTemplate jdbcTemplate;

	@Test
	public void rowMapper() throws Exception {
		RowMapper<DbEntityType> rowMapper = EntityTypeDaoImpl.rowMapper;
		ResultSet resultSet = mock(ResultSet.class);
		String id = "id";
		when(resultSet.getString("id")).thenReturn(id);
		String parentId = "parentId";
		when(resultSet.getString("parentId")).thenReturn(parentId);
		String name = "name";
		when(resultSet.getString("name")).thenReturn(name);
		boolean show = true;
		when(resultSet.getBoolean("show")).thenReturn(show);

		DbEntityType actualDbEntityType = rowMapper.mapRow(resultSet, 0);

		assertNotNull(actualDbEntityType);
		assertEquals(id, actualDbEntityType.getId());
		assertEquals(parentId, actualDbEntityType.getParentId());
		assertEquals(name, actualDbEntityType.getName());
		assertEquals(show, actualDbEntityType.isShow());
	}

	@Test
	public void getAll() {
		String sqlQuery = "select * from entityTypes";
		List<DbEntityType> expectedList = Arrays.asList(new DbEntityType(), new DbEntityType());
		when(jdbcTemplate.query(sqlQuery, rowMapper)).thenReturn(expectedList);

		List<DbEntityType> actualList = testObj.getAll();

		verify(jdbcTemplate).query(sqlQuery, rowMapper);
		assertEquals(expectedList, actualList);
	}

	@Test
	public void get() {
		String id = "id";
		String sqlQuery = "select * from entityTypes where id='" + id + "'";
		DbEntityType expectedDbEntityType = new DbEntityType();
		expectedDbEntityType.setId(id);
		when(jdbcTemplate.queryForObject(sqlQuery, rowMapper)).thenReturn(expectedDbEntityType);

		DbEntityType actualDbEntityType = testObj.get(id);

		verify(jdbcTemplate).queryForObject(sqlQuery, rowMapper);
		assertEquals(expectedDbEntityType, actualDbEntityType);
	}

	@Test
	public void save_notExists_oneRowAffected() throws Exception {
		String id = "id";
		DbEntityType dbEntityType = mock(DbEntityType.class);
		when(dbEntityType.getId()).thenReturn(id);
		String sqlFields = "sql fields";
		when(dbEntityType.getSqlFields()).thenReturn(sqlFields);
		String sqlValues = "sql values";
		when(dbEntityType.getSqlValues()).thenReturn(sqlValues);
		doReturn(null).when(testObj).get(id);
		String sqlQuery = "insert into entityTypes (" + sqlFields + ") values (" + sqlValues + ")";
		when(jdbcTemplate.update(sqlQuery)).thenReturn(1);

		boolean actualReturnValue = testObj.save(dbEntityType);

		verify(jdbcTemplate).update(sqlQuery);
		assertTrue(actualReturnValue);
	}

	@Test
	public void save_notExists_noRowsAffected() throws Exception {
		String id = "id";
		DbEntityType dbEntityType = mock(DbEntityType.class);
		when(dbEntityType.getId()).thenReturn(id);
		String sqlFields = "sql fields";
		when(dbEntityType.getSqlFields()).thenReturn(sqlFields);
		String sqlValues = "sql values";
		when(dbEntityType.getSqlValues()).thenReturn(sqlValues);
		doReturn(null).when(testObj).get(id);
		String sqlQuery = "insert into entityTypes (" + sqlFields + ") values (" + sqlValues + ")";
		when(jdbcTemplate.update(sqlQuery)).thenReturn(0);

		boolean actualReturnValue = testObj.save(dbEntityType);

		verify(jdbcTemplate).update(sqlQuery);
		assertFalse(actualReturnValue);
	}

	@Test
	public void save_notExists_moreRowsAffected() throws Exception {
		String id = "id";
		DbEntityType dbEntityType = mock(DbEntityType.class);
		when(dbEntityType.getId()).thenReturn(id);
		String sqlFields = "sql fields";
		when(dbEntityType.getSqlFields()).thenReturn(sqlFields);
		String sqlValues = "sql values";
		when(dbEntityType.getSqlValues()).thenReturn(sqlValues);
		doReturn(null).when(testObj).get(id);
		String sqlQuery = "insert into entityTypes (" + sqlFields + ") values (" + sqlValues + ")";
		when(jdbcTemplate.update(sqlQuery)).thenReturn(2);

		boolean actualReturnValue = testObj.save(dbEntityType);

		verify(jdbcTemplate).update(sqlQuery);
		assertFalse(actualReturnValue);
	}

	@Test
	public void save_exists_equal() throws Exception {
		String id = "id";
		String name = "name";
		DbEntityType dbEntityType = mock(DbEntityType.class);
		when(dbEntityType.getId()).thenReturn(id);
		when(dbEntityType.getName()).thenReturn(name);
		String sqlFields = "sql fields";
		when(dbEntityType.getSqlFields()).thenReturn(sqlFields);
		String sqlValues = "sql values";
		when(dbEntityType.getSqlValues()).thenReturn(sqlValues);
		doReturn(dbEntityType).when(testObj).get(id);

		boolean actualReturnValue = testObj.save(dbEntityType);

		verifyZeroInteractions(jdbcTemplate);
		assertTrue(actualReturnValue);
	}

	@Test
	public void save_exists_different_oneRowAffected() throws Exception {
		String id = "id";
		String name = "name";
		DbEntityType dbEntityType = mock(DbEntityType.class);
		when(dbEntityType.getId()).thenReturn(id);
		when(dbEntityType.getName()).thenReturn(name);
		String sqlFields = "sql fields";
		when(dbEntityType.getSqlFields()).thenReturn(sqlFields);
		String sqlValues = "sql values";
		when(dbEntityType.getSqlValues()).thenReturn(sqlValues);
		DbEntityType dbEntityTypeDiffName = new DbEntityType();
		dbEntityType.setId(id);
		dbEntityType.setName(name + "diff");
		doReturn(dbEntityTypeDiffName).when(testObj).get(id);
		String sqlQuery = "update entityTypes (" + sqlFields + ") values (" + sqlValues + ") where id='" + id + "'";
		when(jdbcTemplate.update(sqlQuery)).thenReturn(1);

		boolean actualReturnValue = testObj.save(dbEntityType);

		verify(jdbcTemplate).update(sqlQuery);
		assertTrue(actualReturnValue);
	}

	@Test
	public void save_exists_different_noRowsAffected() throws Exception {
		String id = "id";
		String name = "name";
		DbEntityType dbEntityType = mock(DbEntityType.class);
		when(dbEntityType.getId()).thenReturn(id);
		when(dbEntityType.getName()).thenReturn(name);
		String sqlFields = "sql fields";
		when(dbEntityType.getSqlFields()).thenReturn(sqlFields);
		String sqlValues = "sql values";
		when(dbEntityType.getSqlValues()).thenReturn(sqlValues);
		DbEntityType dbEntityTypeDiffName = new DbEntityType();
		dbEntityType.setId(id);
		dbEntityType.setName(name + "diff");
		doReturn(dbEntityTypeDiffName).when(testObj).get(id);
		String sqlQuery = "update entityTypes (" + sqlFields + ") values (" + sqlValues + ") where id='" + id + "'";
		when(jdbcTemplate.update(sqlQuery)).thenReturn(0);

		boolean actualReturnValue = testObj.save(dbEntityType);

		verify(jdbcTemplate).update(sqlQuery);
		assertFalse(actualReturnValue);
	}

	@Test
	public void save_exists_different_moreRowsAffected() throws Exception {
		String id = "id";
		String name = "name";
		DbEntityType dbEntityType = mock(DbEntityType.class);
		when(dbEntityType.getId()).thenReturn(id);
		when(dbEntityType.getName()).thenReturn(name);
		String sqlFields = "sql fields";
		when(dbEntityType.getSqlFields()).thenReturn(sqlFields);
		String sqlValues = "sql values";
		when(dbEntityType.getSqlValues()).thenReturn(sqlValues);
		DbEntityType dbEntityTypeDiffName = new DbEntityType();
		dbEntityType.setId(id);
		dbEntityType.setName(name + "diff");
		doReturn(dbEntityTypeDiffName).when(testObj).get(id);
		String sqlQuery = "update entityTypes (" + sqlFields + ") values (" + sqlValues + ") where id='" + id + "'";
		when(jdbcTemplate.update(sqlQuery)).thenReturn(2);

		boolean actualReturnValue = testObj.save(dbEntityType);

		verify(jdbcTemplate).update(sqlQuery);
		assertFalse(actualReturnValue);
	}
}
