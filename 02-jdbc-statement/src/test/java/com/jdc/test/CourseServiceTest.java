package com.jdc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
	@Order(1)
	@CsvSource({
		"Java Basic, 350000, 2024-03-04, 4, 1",
		"Spring Framework, 450000, 2024-05-02, 5, 2"
	})
	void test_for_course_insert(String name, int fees, 
			LocalDate startDate, int duration, long count) {
		
		service.insert(name, fees, startDate, duration);
		
		assertEquals(count, service.getCount());
		
	}

}









