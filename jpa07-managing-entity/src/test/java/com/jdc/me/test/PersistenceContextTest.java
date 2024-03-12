package com.jdc.me.test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.jdc.me.entity.Account;

@TestMethodOrder(OrderAnnotation.class)
public class PersistenceContextTest extends AbstractTest {
	
	@Test
	@Order(1)
	void test_for_insert_account() {
		var acc1 = new Account("Pyae Phyo", 150000);
		var acc2 = new Account("U Min Khant", 200000);
		
		em.getTransaction().begin();
		em.merge(acc1);
		em.merge(acc2);
		em.getTransaction().commit();
	}

	@ParameterizedTest
	@Order(2)
	@ValueSource(ints = 1)
	@Disabled
	void test_for_find(int id) {
		em.getTransaction().begin();

		var account = em.find(Account.class, 1);
		
		account.setName("U Zaw Min Lwin");
		
		em.getTransaction().commit();
	}
	
	@Test
	@Order(3)
	void test_for_flush_refresh() {
		var em2 = emf.createEntityManager();

		var taskOne = new Thread(() -> {
			var account = em.find(Account.class, 1);

			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			em.getTransaction().begin();
			
			System.out.println(
					"Before task one, amount of %s is %.2f"
						.formatted(account.getName(), 
								account.getAmount()));
			
			em.refresh(account);
			account.setAmount(account.getAmount() + 40000);
			em.flush();
			
			System.out.println(
					"After task one, amount of %s is %.2f"
						.formatted(account.getName(), 
								account.getAmount()));
			
			em.getTransaction().commit();
		});
		
		var taskTwo = new Thread(() -> {
			var account = em2.find(Account.class, 1);

			try {
				Thread.sleep(600);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			em2.getTransaction().begin();
			
			System.out.println(
					"Before task two, amount of %s is %.2f"
						.formatted(account.getName(), 
								account.getAmount()));
			
			em2.refresh(account);
			account.setAmount(account.getAmount() - 50000);
			
			System.out.println(
					"After task two, amount of %s is %.2f"
						.formatted(account.getName(), 
								account.getAmount()));
			
			em2.getTransaction().commit();
		});
		
		taskOne.start();
		taskTwo.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}