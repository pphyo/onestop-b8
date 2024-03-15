package com.jdc.jpql.model.repo;

import java.util.List;
import java.util.function.Function;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

public interface BaseRepository<E> {
	
	List<E> getAll(Function<CriteriaBuilder, CriteriaQuery<E>> queryFunc);
	E getWithId(Function<CriteriaBuilder, CriteriaQuery<E>> queryFunc);

}
