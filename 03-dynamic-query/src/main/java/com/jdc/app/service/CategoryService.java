package com.jdc.app.service;

import java.util.List;

import com.jdc.app.entity.Category;

public interface CategoryService extends BaseService<Category> {
	
	List<Category> findByNameLike(String name);

}
