package com.jdc.app.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
	
	private static final String URL = "jdbc:mysql://localhost:3306/jdbc_db";
	private static final String USR = "os8_user";
	private static final String PWD = "os8_pass";
	
	public static Connection getDbConnection() throws SQLException {
		return DriverManager.getConnection(URL, USR, PWD);
	}
	
	public static void truncateTable(String... tables) {
		try(var conn = getDbConnection();
				var stmt = conn.createStatement()) {
			
			stmt.execute("set foreign_key_checks = 0");
			
			for(String table : tables) {
				stmt.execute("truncate table %s".formatted(table));
			}
			
			stmt.execute("set foreign_key_checks = 1");
			
			System.out.println("Truncate tables successfully...");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}