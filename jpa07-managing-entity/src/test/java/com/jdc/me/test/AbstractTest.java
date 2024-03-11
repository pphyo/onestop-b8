package com.jdc.me.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public abstract class AbstractTest {
	
	static EntityManagerFactory emf;
	EntityManager em;

	@BeforeAll
	static void init() {
		emf = Persistence.createEntityManagerFactory("me");
	}
	
	@BeforeEach
	void start() {
		em = emf.createEntityManager();
	}
	
	@AfterEach
	void stop() {
		if(null != em && em.isOpen())
			em.close();
	}
	
	@AfterAll
	static void shutdown() {
		if(null != emf && emf.isOpen())
			emf.close();
	}
	
}
