package com.jdc.me.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.jdc.me.entity.Ledger;

import jakarta.persistence.EntityNotFoundException;

public class EntitySelectTest extends AbstractTest {
	
	@ParameterizedTest
	@ValueSource(ints = 13)
	void test_for_find(int id) {
		var ledger = em.find(Ledger.class, id);
		assertFalse(em.contains(ledger));
		assertNull(ledger);
	}
	
	@ParameterizedTest
	@ValueSource(ints = 13)
	void test_for_getReference(int id) {
		var ledger = em.getReference(Ledger.class, id);
//		assertNotNull(ledger);
		assertTrue(em.contains(ledger));
//		assertEquals("Credit", ledger.getType().name());
		
		assertThrows(EntityNotFoundException.class, 
				() -> ledger.getName());
	}

}