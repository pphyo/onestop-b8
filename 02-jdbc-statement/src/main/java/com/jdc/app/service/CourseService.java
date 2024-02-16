package com.jdc.app.service;

import static com.jdc.app.service.DatabaseHelper.getDbConnection;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jdc.app.domain.Course;

// CRUD => Create, Retrieve, Update, Delete
public class CourseService {
	
	private final String SELECT = "select * from course";
	
	public void delete(int id) {
		final String DELETE = "delete from course where id = ?";
		
		try(var conn = getDbConnection();
				var stmt = conn.prepareStatement(DELETE)) {
			
			stmt.setInt(1, id);
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void save(Course course) {
		checkCourse(course);
		boolean isUpdate = course.id() > 0;
		
		final String INSERT = """
				insert into course (name, fees, start_date, duration)
				values (?, ?, ?, ?)
				""";
		
		final String UPDATE = """
				update course set name = ?, fees = ?, start_date = ?,
				duration = ? where id = ?
				""";
		
		try(var conn = getDbConnection();
				var stmt = conn.prepareStatement(isUpdate ? UPDATE : INSERT)) {
			
			stmt.setString(1, course.name());
			stmt.setInt(2, course.fees());
			stmt.setDate(3, Date.valueOf(course.startDate()));
			stmt.setInt(4, course.duration());
			
			if(isUpdate) {
				stmt.setInt(5, course.id());
			}
			
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Course findById(int id) {
		
		if(id <= 0)
			throw new RuntimeException("Not found with your %d.".formatted(id));
		
		try(var conn = getDbConnection();
				var stmt = conn.prepareStatement(SELECT.concat(" where id = ?"))) {
			
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
				return Course.from(rs);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Course> findAll() {
		List<Course> result = new ArrayList<>();
		
		try(var conn = getDbConnection();
				var stmt = conn.createStatement()) {
			
			ResultSet rs = stmt.executeQuery(SELECT);
			
			while(rs.next()) {
				result.add(Course.from(rs));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void update(int id, Course course) {

		final String UPDATE = """
				update course set name = ?, fees = ?, start_date = ?,
				duration = ? where id = ?
				""";
		
		try(var conn = getDbConnection();
				var stmt = conn.prepareStatement(UPDATE)) {
			
			stmt.setString(1, course.name());
			stmt.setInt(2, course.fees());
			stmt.setDate(3, Date.valueOf(course.startDate()));
			stmt.setInt(4, course.duration());
			stmt.setInt(5, id);
			
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public long getCount() {
		return findAll().size();
	}
	
	public void insert(Course course) {

		final String INSERT = """
				insert into course (name, fees, start_date, duration)
				values (?, ?, ?, ?)
				""";		
		
		try(var conn = getDbConnection();
				var stmt = conn.prepareStatement(INSERT)) {
			
			stmt.setString(1, course.name());
			stmt.setInt(2, course.fees());
			stmt.setDate(3, Date.valueOf(course.startDate()));
			stmt.setInt(4, course.duration());
			
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void checkCourse(Course course) {
		if(null == course.name() || course.name().isEmpty() || course.name().isBlank())
			throw new RuntimeException("Name can't be empty!");
		
		if(course.fees() <= 0)
			throw new RuntimeException("Fees must be greater than zero!");
		
		if(course.duration() <= 0)
			throw new RuntimeException("Duration must be greater than zero!");
	}

}