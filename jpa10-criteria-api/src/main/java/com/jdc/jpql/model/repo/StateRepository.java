package com.jdc.jpql.model.repo;

import com.jdc.jpql.model.entity.State;

import jakarta.persistence.EntityManager;

public class StateRepository extends BaseRepositoryImpl<State> {
	
	public StateRepository(EntityManager em) {
		super(em);
	}

}
