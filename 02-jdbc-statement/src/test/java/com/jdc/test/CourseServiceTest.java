package com.jdc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.app.domain.Course;
import com.jdc.app.service.CourseService;
import com.jdc.app.service.DatabaseHelper;

@TestMethodOrder(OrderAnnotation.class)
public class CourseServiceTest {
	
	static CourseService service;
	
	@BeforeAll
	static void setUpBeforeClass() {
		service = new CourseService();
		DatabaseHelper.truncateTable("course");
	}
	
	@ParameterizedTest
	@Order(3)
	@CsvSource({"1, 3", "2, 2"})
	void test_for_delete(int id, long remainCount) {
		service.delete(id);
		assertEquals(remainCount, service.getCount());
	}
	
	@ParameterizedTest
	@Order(2)
	@CsvSource("3, Angular Framework, 420000, 2024-01-02, 4")
	void test_for_course_update(int id, String name, int fees, 
				LocalDate startDate, int duration) {
		
		var course = new Course(id, name, fees, startDate, duration);
		service.save(course);
		
		var result = service.findById(id);
		assertEquals(id, result.id());
		assertEquals(name, result.name());
		assertEquals(fees, result.fees());
	}
	
	@ParameterizedTest
	@Order(1)
	@CsvSource({
		"Java Basic, 350000, 2024-03-04, 4, 1",
		"Spring Framework, 450000, 2024-05-02, 5, 2",
		"Angular, 400000, 2024-01-02, 4, 3",
		"One Stop, 950000, 2024-02-10, 6, 4"
	})
	void test_for_course_insert(String name, int fees, 
			LocalDate startDate, int duration, long count) {
		
		Course course = new Course(0, name, fees, startDate, duration);
		service.save(course);
		
		assertEquals(count, service.getCount());
		
	}

}