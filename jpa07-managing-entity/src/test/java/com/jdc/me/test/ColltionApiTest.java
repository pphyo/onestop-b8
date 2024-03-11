package com.jdc.me.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ColltionApiTest {
	
	@Test
	void test() {
		var list = new ArrayList<Integer>();
		Collections.addAll(list, 2, 4, 6, 8, 10, 9, 7, 5, 3, 1);
		
		list.add(10);
		list.set(3, 18);
		
		var of = List.of(2, 4, 6, 8, 10, 9, 7, 5, 3, 1);
		assertAll(() -> {
			assertThrows(UnsupportedOperationException.class, () -> of.add(10));
			assertThrows(UnsupportedOperationException.class, () -> of.remove(0));
			assertThrows(UnsupportedOperationException.class, () -> of.set(0, 20));
			assertThrows(UnsupportedOperationException.class, () -> Collections.sort(of));
		});
		
		var unmodifiableList = Collections.unmodifiableList(list);
		assertAll(() -> {
			assertThrows(UnsupportedOperationException.class, () -> unmodifiableList.add(10));
			assertThrows(UnsupportedOperationException.class, () -> unmodifiableList.remove(0));
			assertThrows(UnsupportedOperationException.class, () -> unmodifiableList.set(0, 20));
		});
		
		var asList = Arrays.asList(2, 4, 6, 8, 10, 9, 7, 5, 3, 1);		
		assertAll(() -> {
			assertThrows(UnsupportedOperationException.class, () -> asList.add(10));
			assertThrows(UnsupportedOperationException.class, () -> asList.remove(0));
			assertEquals(2, asList.set(0, 20));			
			assertEquals(20, asList.get(0));
		});
		
		Collections.sort(asList);
		System.out.println(asList);		
		
	}

}
