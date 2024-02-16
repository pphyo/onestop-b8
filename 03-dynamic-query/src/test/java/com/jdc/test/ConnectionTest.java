package com.jdc.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.jdc.app.support.DatabaseConnector;
import com.jdc.app.support.DatabaseUtil;

public class ConnectionTest {
	
	@Test
	void test_for_create_schema() {
		DatabaseUtil.dropOrTrancateTables(true, "category", "book", "author", "company");
		DatabaseUtil.createTables();
	}
	
	@Test
	void test_for_connection() {
		try {
			var conn = DatabaseConnector.getDatabaseConnection();
			assertNotNull(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
