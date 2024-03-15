package com.jdc.jpql.test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TownshipServiceTest extends TestConfig {
	
	@ParameterizedTest
	@CsvSource({
		"pathein, 4",
		"kyonpyaw, 3",
		"Hinthada, 3",
		"labutta, 2",
		"Maubin, 4",
		"myanmaung, 3",
		"myaungmya, 3",
		"pyapon, 4",
		"bago, 8",
		"taungoo, 6",
		"pyay, 6",
		"tharrawaddy, 8"
	})
	void test_for_townshipCountByDistrict(String name, long count) {
		
	}

}
