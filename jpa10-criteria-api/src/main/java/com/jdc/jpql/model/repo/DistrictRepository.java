package com.jdc.jpql.model.repo;

import com.jdc.jpql.model.entity.District;

import jakarta.persistence.EntityManager;

public class DistrictRepository extends BaseRepositoryImpl<District> {
	
	public DistrictRepository(EntityManager em) {
		super(em);
	}

}
