package com.jdc.app.service;

import static com.jdc.app.service.DatabaseHelper.getDbConnection;

import java.sql.Date;
import java.time.LocalDate;

// CRUD => Create, Retrieve, Update, Delete
public class CourseService {
	
	public long getCount() {
		
		try(var conn = getDbConnection();
				var stmt = conn.createStatement()) {
			
			var rs = stmt.executeQuery("select count(*) from course");
			
			while(rs.next()) {
				return rs.getLong(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public void insert(String name, int fees, 
			LocalDate startDate, int duration) {
		
		final String INSERT = """
				insert into course (name, fees, start_date, duration)
				values (?, ?, ?, ?)
				""";
		
		if(null == name || name.isEmpty() || name.isBlank())
			throw new RuntimeException("Name can't be empty!");
		
		if(fees <= 0)
			throw new RuntimeException("Fees must be greater than zero!");
		
		if(duration <= 0)
			throw new RuntimeException("Duration must be greater than zero!");
		
		try(var conn = getDbConnection();
				var stmt = conn.prepareStatement(INSERT)) {
			
			stmt.setString(1, name);
			stmt.setInt(2, fees);
			stmt.setDate(3, Date.valueOf(startDate));
			stmt.setInt(4, duration);
			
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}