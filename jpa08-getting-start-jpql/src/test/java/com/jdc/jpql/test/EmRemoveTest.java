package com.jdc.jpql.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.jdc.jpql.model.entity.District;
import com.jdc.jpql.model.entity.State;
import com.jdc.jpql.model.entity.Township;

public class EmRemoveTest extends TestConfig {
	
	@Test
	void test_for_find_township() {
		var township = em.find(Township.class, 1);
		assertNotNull(township);
	}
	
	@Test
	@Disabled
	void test_for_orphan_removal() {
		em.getTransaction().begin();
		
		var district = em.find(District.class, 1);
		var township = em.find(Township.class, 1);
		
		district.getTownships().remove(township);
		
		em.merge(district);
		
		em.getTransaction().commit();
	}
	
	@Test
	@Disabled
	void test_for_cascade_remove() {
		em.getTransaction().begin();
		
		var state = em.find(State.class, 1);
		
		em.remove(state);
		
		em.getTransaction().commit();
	}

}