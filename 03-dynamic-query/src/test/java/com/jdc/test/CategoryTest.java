package com.jdc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
		DatabaseUtil.dropOrTrancateTables(false, "category", "book");
	}
	
	@ParameterizedTest
	@Order(2)
	@CsvSource({
		"Science Fiction,5",
		"Actions,1",
		"Adventures,2"
	})
	void test_for_update(String name, int id) {
		var category = new Category();
		category.setId(id);
		category.setName(name);
		
		var actualId = service.save(category);
		var actualCategory = service.findById(actualId);
		assertEquals(name, actualCategory.getName());
	}
	
	@ParameterizedTest
	@Order(3)
	@CsvSource({
		"Actions, 1", 
		"Adventures, 2", 
		"Drama, 3", 
		"Documentary, 4", 
		"Science Fiction, 5"
	})
	void test_for_findById(String name, int id) {
		var actualCategory = service.findById(id);
		assertNotNull(actualCategory);
		assertEquals(name, actualCategory.getName());
	}
	
	@ParameterizedTest
	@Order(4)
	@CsvSource({
		"A,2", "D,2", "ac,1",
		"ad,1", "dr,1", "dec,0",
		"com,0", "Sci,1", "bol,0"
	})
	void test_for_findByNameLike(String param, long count) {
		var list = service.findByNameLike(param);
		assertEquals(count, list.size());
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