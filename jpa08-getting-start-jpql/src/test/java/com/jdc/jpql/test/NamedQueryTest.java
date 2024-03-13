package com.jdc.jpql.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.jdc.jpql.model.entity.State;
import com.jdc.jpql.model.entity.State.Region;

public class NamedQueryTest extends TestConfig {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/states.csv")
	void test_for_select_id_equals(int id, String name,
			String burmese, String capital, Region region, int population) {
		var query = em.createNamedQuery("State.findById", State.class);
		
		query.setParameter("id", id);
		var result = query.getSingleResult();
		
		assertNotNull(result);
		assertAll(() -> {
			assertEquals(id, result.getStateId());
			assertEquals(name, result.getName());
			assertEquals(burmese, result.getBurmese());
			assertEquals(capital, result.getCapital());
			assertEquals(region, result.getRegion());
			assertEquals(population, result.getPopulation());
		});
	}
	
	@Test
	void test_for_state_named_query() {
		
		var namedQuery = em.createNamedQuery("State.getAll", State.class);
		var resultList = namedQuery.getResultList();
		assertEquals(15, resultList.size());
		
	}

}
