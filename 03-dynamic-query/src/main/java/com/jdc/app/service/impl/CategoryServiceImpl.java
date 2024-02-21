package com.jdc.app.service.impl;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import com.jdc.app.entity.Category;
import com.jdc.app.service.CategoryService;
import com.jdc.app.support.DatabaseConnector;
import com.jdc.app.support.QueryAppException;
import com.jdc.app.support.Validators;

public class CategoryServiceImpl implements CategoryService {
	
	private static CategoryServiceImpl INSTANCE;
	
	private CategoryServiceImpl() {}
	
	public static CategoryServiceImpl getInstance() {
		return null == INSTANCE ? INSTANCE = new CategoryServiceImpl() : INSTANCE;
	}

	@Override
	public int save(Category category) {
		if(Objects.isNull(category)) {
			throw new QueryAppException("Category is null!");
		}
		
		if(Validators.isIncorrect(category.getName())) {
			throw new QueryAppException("Please provide correct name!");
		}
		
		final String INSERT = "insert into category (name) values (?)";
		final String UPDATE = "update category set name = ? where id = ?";
		
		Predicate<Category> isUpdate = c -> c.getId() > 0;
		
		var resultId = 0;
		
		try(var conn = DatabaseConnector.getDatabaseConnection();
				var stmt = conn.prepareStatement(isUpdate.test(category) ? UPDATE : INSERT, 
							Statement.RETURN_GENERATED_KEYS)) {
			
			stmt.setString(1, category.getName());
			
			if(isUpdate.test(category))
				stmt.setInt(2, category.getId());
			
			stmt.executeUpdate();
			// select id from category where name = category.name
			if(isUpdate.test(category)) {
				resultId = category.getId();
			} else {
				var rs = stmt.getGeneratedKeys();
				while(rs.next())
					resultId = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultId;
	}
	
	@Override
	public List<Category> findByNameLike(String name) {
		List<Category> result = new ArrayList<>();
		StringBuilder sb = new StringBuilder(selectAll("category"));
		
		if(!Validators.isIncorrect(name)) {
			sb.append(" where lower(name) like ?");
		}
		
		try(var conn = DatabaseConnector.getDatabaseConnection();
				var stmt = conn.prepareStatement(sb.toString())) {
			
			if(!Validators.isIncorrect(name)) {
				stmt.setString(1, name.toLowerCase().concat("%"));
			}
			
			var rs = stmt.executeQuery();
			while(rs.next())
				result.add(Category.from(rs));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}


	@Override
	public List<Category> findAll() {
		return findByNameLike(null);
	}
	

	@Override
	public Category findById(int id) {
		try(var conn = DatabaseConnector.getDatabaseConnection();
				var stmt = conn.prepareStatement(
						selectAll("category").concat(" where id = ?")
						)) {
			
			stmt.setInt(1, id);
			
			var rs = stmt.executeQuery();
			while(rs.next())
				return Category.from(rs);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}