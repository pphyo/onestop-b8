package com.jdc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.jdc.app.entity.Book;
import com.jdc.app.entity.Category;
import com.jdc.app.service.BookService;

@TestMethodOrder(OrderAnnotation.class)
public class BookTest {
	static BookService bookService;
	
	@BeforeAll
	static void init() {
		bookService = BookService.getInstance();
	}
	
	@ParameterizedTest
	@Order(2)
	@CsvFileSource(resources = "/book_data/find_data.txt")
	void test_for_find(String title, String isbn, String categoryName, long count) {
		var list = bookService.find(title, isbn, categoryName);
		assertEquals(count, list.size());
	}
	
	@ParameterizedTest
	@Order(1)
	@CsvFileSource(resources = "/book_data/insert_data.txt")
	void test_for_insert(String title, double price, String isbn, 
			int categoryId, String categoryName, int generatedId) {
		
		var category = new Category();
		category.setId(categoryId);
		category.setName(categoryName);
		
		var book = new Book();
		book.setTitle(title);
		book.setPrice(price);
		book.setIsbn(isbn);
		book.setCategory(category);
		
		var actualId = bookService.save(book);
		assertEquals(generatedId, actualId);
		
	}

}