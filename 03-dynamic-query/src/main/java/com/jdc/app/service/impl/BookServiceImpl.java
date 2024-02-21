package com.jdc.app.service.impl;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import com.jdc.app.entity.Book;
import com.jdc.app.service.BookService;
import com.jdc.app.service.CategoryService;
import com.jdc.app.support.DatabaseConnector;
import com.jdc.app.support.QueryAppException;
import com.jdc.app.support.Validators;

public class BookServiceImpl implements BookService {
	
	private CategoryService categoryService;
	
	public BookServiceImpl() {
		categoryService = CategoryServiceImpl.getInstance();
	}

	@Override
	public int save(Book book) {
		if(Validators.isIncorrect(book.getTitle())) {
			throw new QueryAppException("Please provide book title!");
		}
		
		if(book.getPrice() <= 0) {
			throw new QueryAppException("Please provide correct price!");
		}
		
		if(Objects.isNull(book.getCategory())) {
			throw new QueryAppException("Please provide category!");
		}
		
		return book.getId() > 0 ? update(book.getId(), book) : insert(book);
	}
	
	private int insert(Book book) {
		String INSERT = """
				insert into book (title, price, isbn, category_id) values
				(?, ?, ?, ?)
				""";
		
		try(var conn = DatabaseConnector.getDatabaseConnection();
				var bookStmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
			
			var categoryId = book.getCategory().getId();
			// find category by id
			var category = categoryService.findById(categoryId);
			
			// if not present category, insert
			if(Objects.isNull(category)) {
				categoryId = categoryService.save(book.getCategory());
			}
			
			bookStmt.setString(1, book.getTitle());
			bookStmt.setDouble(2, book.getPrice());
			bookStmt.setString(3, book.getIsbn());
			bookStmt.setInt(4, categoryId);
			
			bookStmt.executeUpdate();
			
			var bookRs = bookStmt.getGeneratedKeys();
			
			while(bookRs.next())
				return bookRs.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	private int update(int id, Book book) {
		return 0;
	}

	@Override
	public List<Book> findAll() {
		return find(null, null, null);
	}

	@Override
	public List<Book> find(String title, String isbn, String categoryName) {
		final String SELECT = """
				select c.id category_id, c.name category_name,
				b.id book_id, b.title, b.price, b.isbn from category c
				join book b on c.id = b.category_id where 1 = 1
				""";
		
		List<Book> result = new ArrayList<>();
		StringBuilder sb = new StringBuilder(SELECT);
		List<String> params = new LinkedList<>();
		
		if(!Validators.isIncorrect(title)) {
			sb.append(" and lower(b.title) like ?");
			params.add(title.toLowerCase().concat("%"));
		}
		
		if(!Validators.isIncorrect(isbn)) {
			sb.append(" and lower(b.isbn) = ?");
			params.add(isbn.toLowerCase());
		}
		
		if(!Validators.isIncorrect(categoryName)) {
			sb.append(" and lower(c.name) like ?");
			params.add(categoryName.toLowerCase().concat("%"));
		}
		
		try(var conn = DatabaseConnector.getDatabaseConnection();
				var stmt = conn.prepareStatement(sb.toString())) {
			
			for(int i = 0; i < params.size(); i++) {
				stmt.setString(i + 1, params.get(i));
			}
			
			var rs = stmt.executeQuery();
			while(rs.next())
				result.add(Book.from(rs));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Book findById(int id) {
		return null;
	}

	@Override
	public void delete(int id) {
		
	}

}
