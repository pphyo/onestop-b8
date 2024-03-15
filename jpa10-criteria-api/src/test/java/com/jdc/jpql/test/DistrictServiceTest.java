package com.jdc.jpql.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.jpql.model.entity.State.Region;
import com.jdc.jpql.model.service.DistrictService;

public class DistrictServiceTest extends TestConfig {
	
	DistrictService service;
	
	@ParameterizedTest
	@CsvSource({
		"East, 4",
		"West, 5",
		"South, 21",
		"North, 37",
		"Central, 18"
	})
	void test_for_countByRegion(Region region, long count) {
		var result = service.getDistrictByRegion(region);
		assertEquals(count, result);
	}
	
	@BeforeEach
	void setUp() {
		service = DistrictService.getInstance(em);
	}

}
