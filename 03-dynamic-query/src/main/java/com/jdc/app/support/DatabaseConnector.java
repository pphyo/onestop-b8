package com.jdc.app.support;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {

	private static Properties props;
	
	static {
		try {
			props = new Properties();
			props.load(new FileInputStream("src/main/resources/db-info.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getDatabaseConnection() throws SQLException {
		return DriverManager.getConnection(
				props.getProperty("app.db.url"),
				props.getProperty("app.db.usr"),
				props.getProperty("app.db.pwd")
				);
	}

}
