package com.jdc.app.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Book {

	private int id;
	private String title;
	private double price;
	private String isbn;
	private Category category;
	
	public static Book from(ResultSet rs) throws SQLException {
		var category = new Category();
		var book = new Book();
		
		category.setId(rs.getInt("category_id"));
		category.setName(rs.getString("category_name"));
		book.setCategory(category);
		book.setId(rs.getInt("book_id"));
		book.setTitle(rs.getString("title"));
		book.setPrice(rs.getDouble("price"));
		book.setIsbn(rs.getString("isbn"));
		
		return book;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
