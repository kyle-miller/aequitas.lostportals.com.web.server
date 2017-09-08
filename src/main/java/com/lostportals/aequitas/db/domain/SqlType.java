package com.lostportals.aequitas.db.domain;

import java.lang.reflect.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.style.ToStringCreator;

public abstract class SqlType {

	private String strFmt = "'%s'";

	public abstract String getId();

	@JsonIgnore
	public String getInsertSqlFields() {
		String sqlFields = "";

		Field[] fields = getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];

			sqlFields += field.getName();
			sqlFields += (i < fields.length - 1) ? ", " : "";
		}

		return sqlFields;
	}

	@JsonIgnore
	public String getInsertSqlValues() throws IllegalAccessException {
		String sqlValues = "";

		Class<? extends SqlType> c = getClass();
		Field[] fields = c.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			field.setAccessible(true);

			sqlValues += String.class.equals(field.getType()) ? String.format(strFmt, field.get(this)) : field.get(this);
			sqlValues += (i < fields.length - 1) ? ", " : "";

			field.setAccessible(false);
		}

		return sqlValues;
	}

	@JsonIgnore
	public String getUpdateSqlSetFieldValuePairs() throws IllegalAccessException {
		String sqlValues = "";

		Class<? extends SqlType> c = getClass();
		Field[] fields = c.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			field.setAccessible(true);

			sqlValues += field.getName() + "=";
			sqlValues += String.class.equals(field.getType()) ? String.format(strFmt, field.get(this)) : field.get(this);
			sqlValues += (i < fields.length - 1) ? ", " : "";

			field.setAccessible(false);
		}

		return sqlValues;
	}

	@Override
	public int hashCode() {
		int hashcode;
		try {
			hashcode = new ObjectMapper().writeValueAsString(this).hashCode();
		} catch (JsonProcessingException e) {
			hashcode = new ToStringCreator(this).toString().hashCode();
		}
		return hashcode;
	}
}
