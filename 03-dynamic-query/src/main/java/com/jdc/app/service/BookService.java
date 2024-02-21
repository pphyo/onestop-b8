package com.jdc.app.service;

import java.util.List;

import com.jdc.app.entity.Book;
import com.jdc.app.service.impl.BookServiceImpl;

public interface BookService extends BaseService<Book> {
	
	public static final BookService service = new BookServiceImpl();
	
	static BookService getInstance() {
		return service;
	}

	void delete(int id);
	
	List<Book> find(String title, String isbn, String categoryName);
	
}
