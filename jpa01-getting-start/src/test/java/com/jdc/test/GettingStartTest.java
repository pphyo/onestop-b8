package com.jdc.test;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GettingStartTest {
	
	@Test
	void test() {
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("jpa01-getting-start");
		
		emf.close();
	}

}










