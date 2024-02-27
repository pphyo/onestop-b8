package com.jdc.test;

import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;

public class CollectionMappingTest {
	
	@Test
	void test() {
		Persistence.createEntityManagerFactory("cm").close();
	}

}
