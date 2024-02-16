package com.jdc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.app.entity.Category;
import com.jdc.app.service.CategoryService;
import com.jdc.app.service.impl.CategoryServiceImpl;
import com.jdc.app.support.DatabaseUtil;

@TestMethodOrder(OrderAnnotation.class)
public class CategoryTest {
	
	static CategoryService service;
	
	@BeforeAll
	static void setUpBeforeClass() {
		service = CategoryServiceImpl.getInstance();
		DatabaseUtil.dropOrTrancateTables(false, "category");
	}
	
	@ParameterizedTest
	@Order(1)
	@CsvSource({
		"Action, 1", "Adventure, 2", "Drama, 3", "Documentary, 4", "Sci-Fi, 5"
	})
	void test_for_insert(String name, int id) {
		var category = new Category();
		category.setName(name);
		int returnId = service.save(category);
		assertEquals(id, returnId);
	}

}









