package com.jdc.jpql.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.jdc.jpql.model.dto.PopulationByRegion;
import com.jdc.jpql.model.entity.State;
import com.jdc.jpql.model.entity.State.Region;

import jakarta.persistence.TypedQuery;

public class JpqlSelectTest extends TestConfig {
	
	@ParameterizedTest
	@CsvSource({
		"South, 16089075",
		"East, 286627",
		"North, 13318021",
		"West, 3188807",
		"Central, 18603723"
	})
	void test_for_select_group_by(Region region, long populationByRegion) {
		String qlString = "select NEW com.jdc.jpql.model.dto.PopulationByRegion(s.region, sum(s.population)) from State s group by s.region having s.region = :region";

		var query = em.createQuery(qlString, PopulationByRegion.class);
		query.setParameter("region", region);
		
		assertEquals(region, query.getParameterValue("region"));
		
		var result = query.getSingleResult();
		assertEquals(region, result.region());
		assertEquals(populationByRegion, result.population());
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/states.csv")
	void test_for_select_id_equals(int id, String name,
			String burmese, String capital, Region region, int population) {
		String qlString = "select s from State s where s.stateId = :id";
		var query = em.createQuery(qlString, State.class);
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

	@ParameterizedTest
	@ValueSource(ints = 4)
	void test_for_select_id_greater_than(int id) {
		String qlString = "select s from State s where s.stateId > :name";
		TypedQuery<State> query = em.createQuery(qlString, State.class);
		query.setParameter("name", id);
		
		var list = query.getResultList();
		assertFalse(list.isEmpty());
		assertEquals(11, list.size());
	}
	
	@Test
	void test_for_select_all() {
		String qlString = "select s from State s";
		
		var query = em.createQuery(qlString, State.class);
		
		var list = query.getResultList();
		
		assertEquals(15, list.size());
		
		var result = list.get(0);
		assertNotNull(result);
		assertAll(() -> {
			assertEquals("Ayeyarwady Region", result.getName());
			assertEquals("ဧရာဝတီတိုင်းဒေသကြီး", result.getBurmese());
			assertEquals("Pathein", result.getCapital());
			assertEquals(Region.South, result.getRegion());
			assertEquals(6184829, result.getPopulation());
		});
	}
	
}
