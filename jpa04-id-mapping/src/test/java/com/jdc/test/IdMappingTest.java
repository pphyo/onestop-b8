package com.jdc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.jdc.im.entity.Transaction;
import com.jdc.im.entity.TransaferLog;

@TestMethodOrder(OrderAnnotation.class)
public final class IdMappingTest extends BaseTest {
	
	@ParameterizedTest
	@ValueSource(ints = { 1 })
	void test_for_basic(int id) {
		var result = em.find(Transaction.class, id);
		assertNotNull(result);
		assertEquals(4, result.getArchives().size());
	}
	
	@ParameterizedTest
	@Order(1)
	@CsvSource({
		"Aung Aung, Thida, 40000" 
	})
	void test_for_sequence(String formName, String toName, double amount) {
		var now = LocalDateTime.now();
		em.getTransaction().begin();
		
		Transaction tx = new Transaction();
		tx.setFromName(formName);
		tx.setToName(toName);
		tx.setAmount(amount);
		tx.setIssuedAt(now);
		tx.getArchives().addAll(List.of("A", "B", "C", "D"));
		
//		TransactionId id = new TransactionId();
//		tx.setTransactionNumber(0);
//		tx.setTransactionTime(now);
		
//		tx.setTxId(id);
		
		TransaferLog log1 = new TransaferLog();
		log1.setFromName(formName);
		log1.setToName(toName);
		log1.setAmount(amount);
		
		TransaferLog log2 = new TransaferLog();
		log2.setFromName(formName);
		log2.setToName(toName);
		log2.setAmount(amount);
		
		em.persist(tx);
		em.persist(log1);
		em.persist(log2);
				
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		em.remove(log2);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		
		TransaferLog log3 = new TransaferLog();
		log3.setFromName("Kyaw Gyi");
		log3.setToName("Ti Lay");
		log3.setAmount(50000);
		
		em.persist(log3);
		
		em.getTransaction().commit();
	}

}