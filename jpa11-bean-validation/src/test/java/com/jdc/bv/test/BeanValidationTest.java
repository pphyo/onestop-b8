package com.jdc.bv.test;

import java.time.LocalDate;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.bv.model.entity.Employee;

import jakarta.persistence.Persistence;

public class BeanValidationTest {
	
	@ParameterizedTest
	@CsvSource({
		"Kyaw Kyaw, 200000, 09777888999, kyawkyaw, 2000-10-20"
	})
	void test(String name, double salary, String phone, String email, LocalDate birthDate) {
		
		var emf = Persistence.createEntityManagerFactory("bv");
		var em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		var emp = new Employee();
		emp.setName(name);
		emp.setSalary(salary);
		emp.setPhone(phone);
		emp.setEmail(email);
		emp.setBirthDate(birthDate);
		
		em.persist(emp);
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
	}
	
}










