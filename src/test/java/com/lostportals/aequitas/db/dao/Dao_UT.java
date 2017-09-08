package com.lostportals.aequitas.db.dao;

import com.lostportals.aequitas.db.domain.SqlType;
import com.lostportals.aequitas.exception.InternalServerException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class Dao_UT {

	public static final String TABLE_NAME = "TABLE_NAME";

	Dao<Type> testObj = null;

	@Mock
	JdbcTemplate jdbcTemplate;

	@Before
	public void before() {
		testObj = spy(new Dao<Type>() {
			RowMapper<Type> getRowMapper() {
				return (rs, rowNum) -> mapFieldsTo(rs, new Type("id"));
			}
			String getTableName() { return TABLE_NAME; }
		});
		testObj.setJdbcTemplate(jdbcTemplate);
	}

	@Test
	public void getAll() {
		List<Type> expectedValue = Collections.singletonList(new Type("objectId"));
		String getAllQuery = "select * from " + TABLE_NAME;
		when(jdbcTemplate.query(eq(getAllQuery), any(RowMapper.class))).thenReturn(expectedValue);

		List<Type> actualValue = testObj.getAll();

		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void get_exists() {
		String id = "objectId";
		Type expectedValue = new Type(id);
		String getByIdQuery = "select * from " + TABLE_NAME + " where id='" + id + "'";
		when(jdbcTemplate.queryForObject(eq(getByIdQuery), any(RowMapper.class))).thenReturn(expectedValue);

		Type actualValue = testObj.get(id);

		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void get_notExists() {
		String id = "objectId";
		String getByIdQuery = "select * from " + TABLE_NAME + " where id='" + id + "'";
		when(jdbcTemplate.queryForObject(eq(getByIdQuery), any(RowMapper.class))).thenThrow(new EmptyResultDataAccessException(1));

		Type actualValue = testObj.get(id);

		assertNull(actualValue);
	}

	@Test
	public void save_new() throws IllegalAccessException {
		String id = "objectId";
		Type objToSave = new Type(id);
		String insertSql = "insert into " + TABLE_NAME + " (" + objToSave.getInsertSqlFields() + ") values (" + objToSave.getInsertSqlValues() + ")";
		when(jdbcTemplate.update(insertSql)).thenReturn(1);

		boolean actualVal = testObj.save(objToSave);

		assertTrue(actualVal);
	}

	@Test
	public void save_equal() throws IllegalAccessException {
		String id = "objectId";
		Type objToSave = new Type(id);
		doReturn(objToSave).when(testObj).get(id);

		boolean actualVal = testObj.save(objToSave);

		assertTrue(actualVal);
	}

	@Test
	public void save_update_success() throws IllegalAccessException {
		String id = "objectId";
		Type objToSave = new Type(id);
		Type getResponseObj = new Type(id);
		getResponseObj.setOtherField(getResponseObj.getOtherField() + "other data");
		doReturn(getResponseObj).when(testObj).get(id);
		String updateSql = "update " + TABLE_NAME + " set " + objToSave.getUpdateSqlSetFieldValuePairs() + " where id='" + id + "'";
		when(jdbcTemplate.update(updateSql)).thenReturn(1);

		boolean actualVal = testObj.save(objToSave);

		assertTrue(actualVal);
	}

	@Test (expected = DataAccessException.class)
	public void save_update_fail() throws IllegalAccessException {
		String id = "objectId";
		Type objToSave = new Type(id);
		Type getResponseObj = new Type(id);
		getResponseObj.setOtherField(getResponseObj.getOtherField() + "other data");
		doReturn(getResponseObj).when(testObj).get(id);
		String updateSql = "update " + TABLE_NAME + " set " + objToSave.getUpdateSqlSetFieldValuePairs() + " where id='" + id + "'";
		when(jdbcTemplate.update(updateSql)).thenThrow(new DataAccessException(""){});

		testObj.save(objToSave);
	}

	@Test
	public void delete_success() {
		String id = "objectId";

		testObj.delete(id);

		String deleteSql = "delete from " + TABLE_NAME + " where id='" + id + "'";
		verify(jdbcTemplate).execute(deleteSql);
	}

	@Test (expected = InternalServerException.class)
	public void delete_fail() {
		String id = "objectId";
		String deleteSql = "delete from " + TABLE_NAME + " where id='" + id + "'";
		doThrow(new DataAccessException(""){}).when(jdbcTemplate).execute(deleteSql);

		testObj.delete(id);
	}

	class Type extends SqlType {
		private String id;
		private String otherField;
		public Type(String id) { this.id = id; this.otherField = "xyz"; }
		public String getId() {
			return id;
		}
		public String getOtherField() { return otherField; }
		public void setOtherField(String otherField) {
			this.otherField = otherField;
		}
	}
}