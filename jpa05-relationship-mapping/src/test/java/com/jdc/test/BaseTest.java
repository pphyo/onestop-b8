package com.jdc.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public sealed class BaseTest permits RelationshipMappingTest {
	
	static EntityManagerFactory emf;
	EntityManager em;
	
	@BeforeAll
	static void setUpBeforeClass() {
		emf = Persistence.createEntityManagerFactory("rm-mysql");
	}
	
	@BeforeEach
	void setUp() {
		em = emf.createEntityManager();
	}
	
	@AfterEach
	void tearDown() {
		if(null != em && em.isOpen()) {
			em.close();
		}
	}
	
	@AfterAll
	static void tearDownAfterClass() {
		if(null != emf && emf.isOpen()) {
			emf.close();
		}
	}

}
