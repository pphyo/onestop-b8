package com.jdc.app.support;

import static com.jdc.app.support.DatabaseConnector.getDatabaseConnection;

public class DatabaseUtil {
	
	public static void createTables() {
		final String CATEGORY = """
				create table category(
					id int primary key auto_increment,
					name varchar(40) not null unique
				)
				""";
		
		final String BOOK = """
				create table book(
					id int primary key auto_increment,
					title varchar(255) not null,
					price decimal(9, 2) not null,
					isbn varchar(10),
					category_id int not null,
					constraint book_category_fk foreign key(category_id) references category(id)
				)
				""";
		
		final String AUTHOR = """
				create table author(
					id int primary key auto_increment,
					name varchar(50) not null,
					gender enum('Male', 'Female', 'Other') not null,
					phone varchar(13)
				)
				""";
		
		final String COMPANY = """
				create table company(
					id int primary key auto_increment,
					published_date DATE not null,
					quantity int not null,
					book_id int not null,
					author_id int not null,
					constraint company_book_fk foreign key(book_id) references book(id),
					constraint company_author_fk foreign key(author_id) references author(id)
				)
				""";
		
		String[] arr = {CATEGORY, BOOK, AUTHOR, COMPANY};
		
		try(var conn = getDatabaseConnection();
				var stmt = conn.createStatement()) {
			
			for(int i = 0; i < arr.length; i++) {
				stmt.addBatch(arr[i]);
			}
			
			stmt.executeBatch();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void dropOrTrancateTables(boolean drop, String... tables) {
		if(tables.length == 0) {
			throw new QueryAppException("Please input at least one table!");
		}
		
		try(var conn = getDatabaseConnection();
				var stmt = conn.createStatement()) {
			
			stmt.execute("set foreign_key_checks = 0");
			
			if(drop) {
				for(String table : tables) {
					stmt.execute("drop table if exists %s".formatted(table));
				}
			} else {
				for(String table : tables) {
					stmt.execute("truncate table %s".formatted(table));
				}
			}
			
			stmt.execute("set foreign_key_checks = 1");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void truncateTables(String... tables) {
		if(tables.length == 0) {
			throw new QueryAppException("Please input at least one table!");
		}
		
		try(var conn = getDatabaseConnection();
				var stmt = conn.createStatement()) {
			
			stmt.execute("set foreign_key_checks = 0");
			
			for(String table : tables) {
				stmt.execute("truncate table %s".formatted(table));
			}
			
			stmt.execute("set foreign_key_checks = 1");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
