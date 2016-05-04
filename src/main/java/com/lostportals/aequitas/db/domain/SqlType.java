package com.lostportals.aequitas.db.domain;

import java.lang.reflect.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class SqlType {
	public abstract String getId();

	@JsonIgnore
	public String getInsertSqlFields() {
		String sqlFields = "";

		Field[] fields = getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];

			sqlFields += field.getName();

			if (i < fields.length - 1) {
				sqlFields += ", ";
			}
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

			if (field.getType() == String.class) {
				sqlValues += "'" + field.get(this) + "'";
			} else {
				sqlValues += field.get(this);
			}

			if (i < fields.length - 1) {
				sqlValues += ", ";
			}

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

			if (field.getType() == String.class) {
				sqlValues += "'" + field.get(this) + "'";
			} else {
				sqlValues += field.get(this);
			}

			if (i < fields.length - 1) {
				sqlValues += ", ";
			}

			field.setAccessible(false);
		}

		return sqlValues;
	}
}
