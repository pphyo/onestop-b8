package com.jdc.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.DriverManager;

import org.junit.jupiter.api.Test;

public class HelloJdbcTest {
	
	@Test
	void test() {
		try(var conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/jdbc_db", 
				"os8_user", "os8_pass");
			var stmt = conn.createStatement()) {
			
			assertNotNull(conn);
			
			String table = """
					create table course(
					id int primary key auto_increment,
					name varchar(50) not null unique,
					fees decimal(9, 2) not null,
					start_date date default (current_date),
					duration tinyint not null);
					""";
			
			boolean result = stmt.execute(table);
			assertFalse(result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}












