package com.jdog.frameworks.entity;

public class EntityCheckError extends BaseEntity {

	private String entity;
	private String field;
	private Object value;
	private String error;
	
	public EntityCheckError(String entity, String field, Object value, String error) {
		this.entity = entity;
		this.field = field;
		this.value = value;
		this.error = error;
	}
	
	public String toString() {
		return entity + "." + field + "=" + value + "__" + error;
	}
	
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
}
