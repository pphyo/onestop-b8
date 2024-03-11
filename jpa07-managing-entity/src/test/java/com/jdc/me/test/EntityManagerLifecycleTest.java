package com.jdc.me.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hibernate.PersistentObjectException;
import org.junit.jupiter.api.Test;

import com.jdc.me.entity.Ledger;
import com.jdc.me.entity.Ledger.LedgerType;

public class EntityManagerLifecycleTest extends AbstractTest {
	
	@Test
	void test1() {
		
		// transient state or new state
		var ledger = new Ledger();
		ledger.setName("Baby");
		ledger.setType(LedgerType.Debit);
		
		var tx = em.getTransaction();
		tx.begin();
		
		// managed state
		em.persist(ledger);
		ledger.setName("Betting");
		assertTrue(em.contains(ledger));
		
		// managed state to detached state
		em.detach(ledger);
		assertFalse(em.contains(ledger));

		assertThrows(IllegalArgumentException.class, 
				() -> em.remove(ledger));
		
		assertThrows(PersistentObjectException.class, 
				() -> em.persist(ledger));

		// detached state to managed state (return type)
		var managedLedger = em.merge(ledger);
		
		assertTrue(em.contains(managedLedger));
		
		tx.commit();
		
	}
	
	@Test
	void test2() {
		// managed state
		var ledger = new Ledger();
		ledger.setName("Cupons");
		ledger.setType(LedgerType.Credit);
		
		em.getTransaction().begin();
		
		em.persist(ledger);
		assertTrue(em.contains(ledger));
		
		em.remove(ledger);
		assertFalse(em.contains(ledger));
		
		assertThrows(IllegalArgumentException.class, 
				() -> em.merge(ledger));
		
		em.persist(ledger);
		assertTrue(em.contains(ledger));
		
		em.getTransaction().commit();
	}

}







