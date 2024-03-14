package com.jdc.jpql.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.jdc.jpql.model.entity.State.Region;
import com.jdc.jpql.model.service.StateService;

public class CriteriaApiTest extends TestConfig {
	
	StateService stateService;
	
	@ParameterizedTest
	@CsvSource({
		"a,,0,1",
		"b,,0,1",
		"c,,0,1",
		"ka,,0,3",
		"ka,Loi,0,1",
		"M,,0,3",
		"ma,,0,2",
		"state,,0,7",
		"region,,0,7",
		"union,,0,1"
	})
	void test_for_dynamic_find(String name, String capital, int population, int count) {
		var list = stateService.find(name, capital, population);
		assertEquals(count, list.size());
	}
	
	@ParameterizedTest
	@CsvSource({
		"East, 1",
		"West, 1",
		"South, 5",
		"North, 4",
		"Central, 4"
	})
	void test_for_findByRegion(Region region, int count) {
		var list = stateService.findByRegion(region);
		assertEquals(count, list.size());
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/states.csv")
	void test_for_findById(int id, String name, String burmese, String capital, Region region, int population) {
		var state = stateService.findById(id);
		assertNotNull(state);
		assertAll(
					() -> assertEquals(id, state.getStateId()),
					() -> assertEquals(name, state.getName()),
					() -> assertEquals(burmese, state.getBurmese()),
					() -> assertEquals(capital, state.getCapital()),
					() -> assertEquals(region, state.getRegion()),
					() -> assertEquals(population, state.getPopulation())
				);
		
	}

	@ParameterizedTest
	@ValueSource(ints = 15)
	void test_for_findAll(int result) {
		var list = stateService.findAll();
		assertEquals(result, list.size());
	}
	
	@BeforeEach
	void setUp() {
		stateService = StateService.getInstance(em);
	}
	
}