package com.jdc.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;

import com.jdc.im.entity.Book;
import com.jdc.im.entity.Company;
import com.jdc.im.entity.Electronic;

public class InheritanceMappingTest {
	
	@Test
	void test() {
		var emf = Persistence.createEntityManagerFactory("im");
		var em = emf.createEntityManager();
		
		var tx = em.getTransaction();
		
		tx.begin();
		
		var book = new Book();
		
		// does Book instance assign the object of book variable?
		//System.out.println(book instanceof Product);
		
		book.setName("Myanmar Drama Book");
		book.setPrice(10000);
		book.setStock(20);
		book.setTitle("Ah kyin thu the");
		book.setIsbn("BK09393");
		
		var mobile = new Electronic();
		mobile.setName("Redmi Global Note Series");
		mobile.setPrice(799000);
		mobile.setStock(2);
		mobile.setModel("Note 12 Turbo");
		
		var com = new Company("Xiaomi", LocalDate.now(), "Beijin");
		
		mobile.setMainCompany(com);
		
		em.persist(book);
		em.persist(mobile);
		
		var thoeSaung = em.find(Book.class, 1);
		
		assertAll(() -> {
			assertEquals("Myanmar Drama Book", thoeSaung.getName());
			assertEquals(10000, thoeSaung.getPrice());
			assertEquals(20, thoeSaung.getStock());
			assertEquals("Ah kyin thu the", thoeSaung.getTitle());
			assertEquals("BK09393", thoeSaung.getIsbn());
		});
		
		tx.commit();
		
		em.close();
		emf.close();
	}

}
