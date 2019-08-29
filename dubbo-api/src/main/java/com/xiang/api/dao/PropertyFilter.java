package com.xiang.api.dao;


import java.io.Serializable;


public class PropertyFilter implements Serializable {

	private String sql = null;

	public PropertyFilter() {

	}
	public PropertyFilter(String sql) {
		this.sql = sql;
	}


	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
}
