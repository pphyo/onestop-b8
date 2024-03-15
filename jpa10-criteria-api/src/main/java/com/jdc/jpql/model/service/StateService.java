package com.jdc.jpql.model.service;

import static com.jdc.jpql.model.support.StringUtils.hasLength;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.jdc.jpql.model.CriteriaException;
import com.jdc.jpql.model.entity.State;
import com.jdc.jpql.model.entity.State.Region;
import com.jdc.jpql.model.entity.State_;
import com.jdc.jpql.model.repo.StateRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;

public class StateService {
	
	private static StateService service;
	
	private StateRepository repo;
	
	private StateService(EntityManager em) {
		repo = new StateRepository(em);
	}
	
	public static StateService getInstance(EntityManager em) {
		if(null == service)
			service = new StateService(em);
		return service;
	}
	
	public List<State> find(String name, String capital, int population) {
		
		Function<CriteriaBuilder, CriteriaQuery<State>> queryFunc = cb -> {
			var cq = cb.createQuery(State.class);
			var root = cq.from(State.class);
			
			List<Predicate> predicates = new ArrayList<>();	
			// name like
			if(hasLength(name))
				predicates.add(cb.like(cb.lower(root.get(State_.name)), "%s%s".formatted(name.toLowerCase(), "%")));
			
			// capital like
			if(hasLength(capital))
				predicates.add(cb.like(cb.lower(root.get(State_.capital)), "%s%s".formatted(capital.toLowerCase(), "%")));
			
			// population greater than equal
			if(population > 0)
				predicates.add(cb.greaterThanOrEqualTo(root.get(State_.population), population));
			
			cq.select(root).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
			
			return cq;
		};
		
		return repo.getAll(queryFunc);
	}
	
	// select * from state where id = ?
	public State findById(int id) {
		try {
			/* var cb = em.getCriteriaBuilder();
			var cq = cb.createQuery(State.class);
			var root = cq.from(State.class);
			
			cq.select(root).where(cb.equal(root.get("stateId"), id)); */
			
			Function<CriteriaBuilder, CriteriaQuery<State>> queryFunc = cb -> {
				var cq = cb.createQuery(State.class);
				var root = cq.from(State.class);
				
				cq.select(root).where(cb.equal(root.get(State_.stateId), id));
				
				return cq;
			};
			
			return repo.getWithId(queryFunc);
		} catch(NoResultException e) {
			throw new CriteriaException("No record found for state with ID %d.".formatted(id));
		}
	}
	
	// select * from state where region = ?
	public List<State> findByRegion(Region region) {
		/* var cb = em.getCriteriaBuilder();
		var cq = cb.createQuery(State.class);
		var root = cq.from(State.class);
		
		cq.select(root).where(cb.equal(root.get("region"), region)); */
		
		Function<CriteriaBuilder, CriteriaQuery<State>> queryFunc = cb -> {
			var cq = cb.createQuery(State.class);
			var root = cq.from(State.class);
			cq.select(root).where(cb.equal(root.get(State_.region), region)).orderBy(cb.desc(root.get("name")));
			return cq;
		};
		
		return repo.getAll(queryFunc);
	}
	
	// select * from state
	public List<State> findAll() {
		/* / create criteria builders
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		// create criteria query
		CriteriaQuery<State> cq = cb.createQuery(State.class);
		
		// create root
		Root<State> root = cq.from(State.class);
		
		// select s from State s
		cq.select(root); */
		
		
		Function<CriteriaBuilder, CriteriaQuery<State>> queryFunc = cb -> {
			var cq = cb.createQuery(State.class);
			return cq.select(cq.from(State.class));
		};
		
		return repo.getAll(queryFunc);
	}
	
}