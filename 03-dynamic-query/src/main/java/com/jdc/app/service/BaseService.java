package com.jdc.app.service;

import java.util.List;

public interface BaseService<T> {
	
	default String selectAll(String name) {
		return "select * from %s".formatted(name);
	}
	
	/**
	 * 
	 * @param entity type
	 * @return generated primary key
	 */
	int save(T t);
	
	/**
	 * 
	 * @return collection of entity
	 */
	List<T> findAll();
	
	/**
	 * 
	 * @param entity's id
	 * @return entity given selected id
	 */
	T findById(int id);

}
