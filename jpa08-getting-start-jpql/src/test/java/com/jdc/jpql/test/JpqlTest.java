package com.jdc.jpql.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.jpql.model.entity.State;

public class JpqlTest extends TestConfig {
	
	@Test
	void test_for_jpql_delete_query() {
		em.getTransaction().begin();
		
		String qlString = "delete from TownshipEntity t where t.id = (select max_id from (select max(te.id) as max_id from TownshipEntity te) as twp)";
		var query = em.createQuery(qlString);
		query.executeUpdate();
		
		em.getTransaction().commit();
	}
	
	@Test
	void test_for_jpql_update_query() {
		em.getTransaction().begin();
		
		String qlString = "update State s set s.name = 'Ayeyarwady' where s.id = 1";
		var query = em.createQuery(qlString);
		query.executeUpdate();
		
		em.getTransaction().commit();
	}
	
	@Test
	@CsvSource({
		"Tedim, တီးတိန်, 13",
		"Tonzang, တွန်းဇံ, 13",
		"Cikha, ကျီခါး, 13",
		"Khaikam, ခိုင်ကမ်း, 13"
	})
	void test_for_jpql_insert_query() { // (String name, String burmese, int districtId) {
		assertThrows(IllegalArgumentException.class, () -> {
			em.getTransaction().begin();
			
			String qlString = "insert into Township t (t.name, t.burmese, t.district.id) values ('Tedim', 'တီးတိန်', 13)";
			var query = em.createQuery(qlString);
			query.executeUpdate();
			
			em.getTransaction().commit();
		});
	}
	
	@Test
	@Disabled
	void test_for_typed_query() {
		String jpql = "select count(s) from State s";
		
		var query = em.createQuery(jpql, Long.class);
		assertEquals(15L, query.getSingleResult());
	}
	
	@Test
	@Disabled
	void test_for_query() {
		String jpql = "select s from State s";
		
		var query = em.createQuery(jpql);
		
		@SuppressWarnings("unchecked")
		List<Object> list = query.getResultList();
		
		list.stream()
			.map(obj -> {
				if(obj instanceof State state)
					return state;
				return null;
			})
			.forEach(state -> System.out.println(state.getName()));
	}

}