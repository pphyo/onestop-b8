package com.jdc.jpql.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.jdc.jpql.model.entity.State;
import com.jdc.jpql.model.entity.State.Region;

import jakarta.persistence.TypedQuery;

public class JpqlSelectTest extends TestConfig {
	
	@ParameterizedTest
	@CsvSource({
		"1, Ayeyarwady Region, ဧရာဝတီတိုင်းဒေသကြီး, Pathein, South, 6184829",
		"2, Bago Region, ပဲခူးတိုင်းဒေသကြီး, Bago, South, 4867373",
		"3, Chin State, ချင်းပြည်နယ်, Hakha, North, 478801",
		"4, Kachin State, ကချင်ပြည်နယ်, Myitkyina, North, 1689441",
		"5, Kayah State, ကယားပြည်နယ်, Loikaw, East, 286627",
		"6, Kayin State, ကရင်ပြည်နယ်, Hpa-an, South, 1574079",
		"7, Magway Region, မကွေးတိုင်းဒေသကြီး, Magwe, Central, 3917055",
		"8, Mandalay Region, မန္တလေးတိုင်းဒေသကြီး, Mandalay, Central, 6165723",
		"9, Mon State, မွန်ပြည်နယ်, Mawlamyine, South, 2054393",
		"10, Naypyidaw Union Territory, နေပြည်တော် ပြည်ထောင်စုနယ်မြေ, Naypyidaw, Central, 1160242",
		"11, Rakhine State, ရခိုင်ပြည်နယ်, Sittwe, West, 3188807",
		"12, Sagaing Region, စစ်ကိုင်းတိုင်းဒေသကြီး, Monywa, North, 5325347",
		"13, Shan State, ရှမ်းပြည်နယ်, Taunggyi, North, 5824432",
		"14, Tanintharyi Region, တနင်္သာရီတိုင်းဒေသကြီး, Dawei, South, 1408401",
		"15, Yangon Region, ရန်ကုန်တိုင်းဒေသကြီး, Yangon, Central, 7360703"
	})
	void test_for_select_id_equals(int id, String name,
			String burmese, String capital, Region region, int population) {
		String qlString = "select s from State s where s.stateId = :id";
		var query = em.createQuery(qlString, State.class);
		query.setParameter("id", id);
		var result = query.getSingleResult();
		assertNotNull(result);
		assertAll(() -> {
			assertEquals(id, result.getStateId());
			assertEquals(name, result.getName());
			assertEquals(burmese, result.getBurmese());
			assertEquals(capital, result.getCapital());
			assertEquals(region, result.getRegion());
			assertEquals(population, result.getPopulation());
		});
	}

	@ParameterizedTest
	@ValueSource(ints = 4)
	void test_for_select_id_greater_than(int id) {
		String qlString = "select s from State s where s.stateId > :name";
		TypedQuery<State> query = em.createQuery(qlString, State.class);
		query.setParameter("name", id);
		
		var list = query.getResultList();
		assertFalse(list.isEmpty());
		assertEquals(11, list.size());
	}
	
	@Test
	void test_for_select_all() {
		String qlString = "select s from State s";
		
		var query = em.createQuery(qlString, State.class);
		
		var list = query.getResultList();
		
		assertEquals(15, list.size());
		
		var result = list.get(0);
		assertNotNull(result);
		assertAll(() -> {
			assertEquals("Ayeyarwady Region", result.getName());
			assertEquals("ဧရာဝတီတိုင်းဒေသကြီး", result.getBurmese());
			assertEquals("Pathein", result.getCapital());
			assertEquals(Region.South, result.getRegion());
			assertEquals(6184829, result.getPopulation());
		});
	}
	
}
