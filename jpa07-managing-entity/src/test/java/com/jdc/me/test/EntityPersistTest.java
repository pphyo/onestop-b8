package com.jdc.me.test;

import java.util.ArrayList;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.me.entity.Ledger;
import com.jdc.me.entity.Ledger.LedgerType;
import com.jdc.me.entity.Transaction;

public class EntityPersistTest extends AbstractTest {
	
	@ParameterizedTest
	@CsvSource({
		"Cupons,Credit,Nami,Luffy,Zoro"
	})
	void test_for_unidirection_persist(
			String ledgerName, LedgerType ledgerType,
			String iu1, String iu2, String iu3) {
		
		var ledger = new Ledger();
		ledger.setName(ledgerName);
		ledger.setType(ledgerType);
		
		var tx1 = new Transaction();
		tx1.setIssuer(iu1);
		
		var tx2 = new Transaction();
		tx2.setIssuer(iu2);
		
		var tx3 = new Transaction();
		tx3.setIssuer(iu3);
		
		// Bi directional relationship start
		
		// Many to one uni directional relationship start
//		tx1.setLedger(ledger);
//		tx2.setLedger(ledger);
//		tx3.setLedger(ledger);
		// Many to one uni directional relationship end
		
		// One to many uni directional relationship start
//		ledger.setTransactions(List.of(tx1, tx2, tx3));
		// One to many uni directional relationship stop
		
		// Bi directional relationship stop
		
		ledger.setTransactions(new ArrayList<>());
		
		ledger.addTransaction(tx1);
		ledger.addTransaction(tx2);
		ledger.addTransaction(tx3);
		
		em.getTransaction().begin();
		
		em.persist(ledger);
		
		em.getTransaction().commit();
	}

}








