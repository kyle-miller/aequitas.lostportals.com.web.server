package com.lostportals.aequitas.db.domain;

import java.lang.reflect.Field;

import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lostportals.aequitas.web.domain.EntityType;

public class DbEntityType {
	private String id;
	private String parentId;
	private String name;
	private boolean show;

	public DbEntityType() {

	}

	public DbEntityType(EntityType entityType) {
		this.id = entityType.getId();
		this.parentId = entityType.getParentId();
		this.name = entityType.getName();
		this.show = entityType.isShow();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public String toString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return new ToStringCreator(this).toString();
		}
	}

	public String getSqlFields() {
		String sqlFields = "";

		Field[] fields = getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			
			sqlFields += field.getName();
			
			if (i < fields.length) {
				sqlFields += ", ";
			}
		}

		return sqlFields;
	}

	public String getSqlValues() throws IllegalAccessException {
		String sqlValues = "";
		
		Class<? extends DbEntityType> c = getClass();
		Field[] fields = c.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			
			if (field.getType() == String.class) {
				sqlValues += "'" + field.get(this) + "'"; 
			} else {
				sqlValues += field.get(this);
			}
			
			if (i < fields.length) {
				sqlValues += ", ";
			}
		}
		
		return sqlValues;
	}
}
