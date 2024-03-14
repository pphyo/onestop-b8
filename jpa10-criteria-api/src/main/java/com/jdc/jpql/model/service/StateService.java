package com.jdc.jpql.model.service;

import java.util.List;

import com.jdc.jpql.model.entity.State;
import com.jdc.jpql.model.entity.State.Region;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class StateService {
	
	private static StateService service;
	
	private EntityManager em;
	
	private StateService(EntityManager em) {
		this.em = em;
	}
	
	public static StateService getInstance(EntityManager em) {
		if(null == service)
			service = new StateService(em);
		return service;
	}
	
	public List<State> find(String name, String capital, int population) {
		var cb = em.getCriteriaBuilder();
		var cq = cb.createQuery(State.class);
		var root = cq.from(State.class);
		
		cq.select(root).where(
				// name like
				cb.like(cb.lower(root.get("name")), name.toLowerCase()),
				
				// capital like
				cb.like(cb.lower(root.get("capital")), capital.toLowerCase()),
				
				// population greater than equal
				cb.greaterThanOrEqualTo(root.get("population"), population)
				);
		
		return em.createQuery(cq).getResultList();
	}
	
	// select * from state where id = ?
	public State findById(int id) {
		var cb = em.getCriteriaBuilder();
		var cq = cb.createQuery(State.class);
		var root = cq.from(State.class);
		
		cq.select(root).where(cb.equal(root.get("stateId"), id));
		
		return em.createQuery(cq).getSingleResult();
	}
	
	// select * from state where region = ?
	public List<State> findByRegion(Region region) {
		var cb = em.getCriteriaBuilder();
		var cq = cb.createQuery(State.class);
		var root = cq.from(State.class);
		
		cq.select(root).where(cb.equal(root.get("region"), region));
		
		return em.createQuery(cq).getResultList();
	}
	
	// select * from state
	public List<State> findAll() {
		// create criteria builders
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		// create criteria query
		CriteriaQuery<State> cq = cb.createQuery(State.class);
		
		// create root
		Root<State> root = cq.from(State.class);
		
		// select s from State s
		cq.select(root);
		
		return em.createQuery(cq).getResultList();
	}
	
}
