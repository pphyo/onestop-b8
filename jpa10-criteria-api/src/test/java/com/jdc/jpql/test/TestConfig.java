package com.jdc.jpql.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestConfig {
	
	static EntityManagerFactory emf;
	static EntityManager em;
	
	@BeforeAll
	static void init() {
		emf = Persistence.createEntityManagerFactory("criteria");
		em = emf.createEntityManager();
	}
	
	@AfterAll
	static void shutdown() {
		if(null != em && em.isOpen())
			em.close();
		
		if(null != emf && emf.isOpen())
			emf.close();
	}

}
