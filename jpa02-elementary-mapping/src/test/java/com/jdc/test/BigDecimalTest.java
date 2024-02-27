package com.jdc.test;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class BigDecimalTest {
	
	@Test
	void test() {
		BigDecimal result = new BigDecimal(0);
		
		for(int i = 0; i < 10; i++) {
			result = result.add(new BigDecimal(0.1));
		}
		
		System.out.println(result);
	}

}
