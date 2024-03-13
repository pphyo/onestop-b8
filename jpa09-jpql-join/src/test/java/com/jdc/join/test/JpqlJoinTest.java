package com.jdc.join.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.jdc.join.model.entity.Category;
import com.jdc.join.model.entity.Contact;
import com.jdc.join.model.entity.Course;
import com.jdc.join.model.entity.Registration;
import com.jdc.join.model.entity.Student;
import com.jdc.join.model.entity.constants.Gender;
import com.jdc.join.model.entity.constants.Level;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@TestMethodOrder(OrderAnnotation.class)
public class JpqlJoinTest {
	
	static EntityManagerFactory emf;
	EntityManager em;
	
	@Test
	void test_for_jpql_join() {
		var result = em.find(Student.class, 1);
		var address = result.getContact().getAddress();
		assertEquals("Yangon", address);
		assertNotNull(result);
	}
	
	@Test
	@Order(1)
	void load_initial_data() {
		var categoryList = List.of(new Category("Programming"), 
						new Category("Networking"), 
						new Category("Hardware"));
		
		var course = new Course();
		course.setName("Ethical Hacking");
		course.setLevel(Level.Intermediate);
		course.setDuration(4);
		course.setStartDate(LocalDate.of(2023, 10, 2));
		course.setCategories(categoryList);
		
		var course2 = new Course();
		course2.setName("Advanced A+");
		course2.setLevel(Level.Advanced);
		course2.setDuration(8);
		course2.setStartDate(LocalDate.of(2023, 12, 1));
		course.setCategories(categoryList);
		
		var course3 = new Course();
		course3.setName("One Stop");
		course3.setLevel(Level.Advanced);
		course3.setDuration(6);
		course3.setStartDate(LocalDate.of(2024, 1, 4));
		course3.setCategories(List.of(new Category("Java"), 
				new Category("Spring Framework"), 
				new Category("Angular")));
		
		var contact1 = new Contact("luffy@gmail.com", "0910002000", "Yangon");
		var contact2 = new Contact("nami@gmail.com", "0930004000", "Mandalay");
		var contact3 = new Contact("zoro@gmail.com", "0950006000", "Sagaing");
		
		Student stu1 = new Student();
		stu1.setId(1);
		stu1.setName("Luffy");
		stu1.setGender(Gender.Male);
		stu1.setContact(contact1);
		
		Student stu2 = new Student();
		stu2.setId(2);
		stu2.setName("Nami");
		stu2.setGender(Gender.Female);
		stu2.setContact(contact2);
		
		Student stu3 = new Student();
		stu3.setId(3);
		stu3.setName("Zoro");
		stu3.setGender(Gender.Male);
		stu3.setContact(contact3);
		
		var reg1 = new Registration();
		reg1.setRegDate(LocalDate.of(2023, 9, 20));
		reg1.setRegFees(30000);
		reg1.setCourse(course);
		reg1.setStudent(stu1);
		
		var reg2 = new Registration();
		reg2.setRegDate(LocalDate.of(2023, 12, 27));
		reg2.setRegFees(50000);
		reg2.setCourse(course3);
		reg2.setStudent(stu2);
		
		em.getTransaction().begin();
		
		em.persist(course);
		em.persist(course2);
		em.persist(course3);
		
		em.persist(stu1);
		em.persist(stu2);
		em.persist(stu3);
		
		em.persist(reg1);
		em.persist(reg2);
		
		em.getTransaction().commit();
		
	}
	
	@BeforeAll
	static void init() {
		emf = Persistence.createEntityManagerFactory("jpql-join");
	}
	
	@BeforeEach
	void start() {
		em = emf.createEntityManager();
	}
	
	@AfterEach
	void stop() {
		if(null != em && em.isOpen())
			em.close();
	}
	
	@AfterAll
	static void shutdown() {
		if(null != emf && emf.isOpen())
			emf.close();
	}

}
