package com.jdc.app.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Category {

	private int id;
	private String name;
	
	public static Category from(ResultSet rs) throws SQLException {
		var category = new Category();
		category.setId(rs.getInt("id"));
		category.setName(rs.getString("name"));
		return category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
