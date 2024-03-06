package com.jdc.test;

import org.junit.jupiter.api.Test;

import com.jdc.rm.entity.Hero;
import com.jdc.rm.entity.Hero.Difficulty;
import com.jdc.rm.entity.HeroRole;

public final class RelationshipMappingTest extends BaseTest {
	
	@Test
	void test() {
		var tx = em.getTransaction();
		
		tx.begin();
		
		Hero hero = new Hero();
		hero.setName("Chou");
		hero.setDifficulty(Difficulty.Hard);
		
		em.persist(hero);
		
		HeroRole role = new HeroRole();
		role.setRoleName("Fighter");
		role.setHero(hero);
		
		em.persist(role);
		
		tx.commit();
	}

}







