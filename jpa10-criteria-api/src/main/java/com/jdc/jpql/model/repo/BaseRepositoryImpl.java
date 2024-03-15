package com.jdc.jpql.model.repo;

import java.util.List;
import java.util.function.Function;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

public class BaseRepositoryImpl<E> implements BaseRepository<E> {
	
	private EntityManager em;
	
	public BaseRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<E> getAll(Function<CriteriaBuilder, CriteriaQuery<E>> queryFunc) {
		return em.createQuery(queryFunc.apply(em.getCriteriaBuilder()))
					.getResultList();
	}

	@Override
	public E getWithId(Function<CriteriaBuilder, CriteriaQuery<E>> queryFunc) {
		return em.createQuery(queryFunc.apply(em.getCriteriaBuilder()))
					.getSingleResult();
	}

}