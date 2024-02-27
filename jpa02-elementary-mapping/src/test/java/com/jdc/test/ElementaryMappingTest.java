package com.jdc.test;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;

import com.jdc.app.entity.Account;

public class ElementaryMappingTest {
	
	@Test
	void test() {
		var emf = Persistence.createEntityManagerFactory("em");
		var em = emf.createEntityManager();
		
		var tx = em.getTransaction();
		
		tx.begin();
		
		var acc = new Account();
		acc.setId(1);
		acc.setUsername("pphyo");
		acc.setEmail("pyaephyo.jdc@gmail.com");
		acc.setPassword("pyaephyo");
//		acc.setBirthDate(new Date(2000, 10, 2));
		acc.setAvgActiveRate(new BigDecimal(new BigInteger("100")));
		
		em.persist(acc);
		
		tx.commit();
		
		em.close();
		emf.close();
	}

}