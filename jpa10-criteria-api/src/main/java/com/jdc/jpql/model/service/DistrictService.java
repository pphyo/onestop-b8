package com.jdc.jpql.model.service;

import java.util.function.Function;

import com.jdc.jpql.model.entity.District;
import com.jdc.jpql.model.entity.District_;
import com.jdc.jpql.model.entity.State.Region;
import com.jdc.jpql.model.entity.State_;
import com.jdc.jpql.model.repo.DistrictRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

public class DistrictService {
	
	private static DistrictService instance;
	
	private DistrictRepository repo;
	
	private DistrictService(EntityManager em) {
		repo = new DistrictRepository(em);
	}
	
	public static DistrictService getInstance(EntityManager em) {
		return null == instance ? instance = new DistrictService(em) : instance;
	}
	
	public Long getDistrictByRegion(Region region) {
		Function<CriteriaBuilder, CriteriaQuery<Long>> counFunc = cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(District.class);
			
			root.join(District_.state);
			
			cq.select(cb.count(root.get(District_.id)))
					.where(cb.equal(root.get(District_.state).get(State_.region), region))
					.groupBy(root.get(District_.state).get(State_.region));
			
			return cq;
		};
		
		return repo.count(counFunc);
	}

}











