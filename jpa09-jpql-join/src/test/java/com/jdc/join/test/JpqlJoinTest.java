package com.jdc.join.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.join.model.dto.BookByWholesalePrice;
import com.jdc.join.model.entity.Category;
import com.jdc.join.model.entity.Contact;
import com.jdc.join.model.entity.Course;
import com.jdc.join.model.entity.Registration;
import com.jdc.join.model.entity.Student;
import com.jdc.join.model.entity.constants.Gender;
import com.jdc.join.model.entity.constants.Level;
import com.jdc.join.model.entity.constants.WholesaleType;
import com.jdc.join.model.entity.treat.Book;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@TestMethodOrder(OrderAnnotation.class)
public class JpqlJoinTest {
	
	static EntityManagerFactory emf;
	EntityManager em;
	
	@ParameterizedTest
	@Order(7)
	@CsvSource({
		"35000, 4",
		"36000, 2",
		"37000, 1",
		"38000, 0"
	})
	void test_for_path_expression(double price, int count) {
		String qlString = """
				select NEW 
				com.jdc.join.model.dto.BookByWholesalePrice(b.name, KEY(ws), VALUE(ws)) 
				from Book b join b.wholesalePrice ws where VALUE(ws) > :wsprice""";
		
		var query = em.createQuery(qlString, BookByWholesalePrice.class);
		query.setParameter("wsprice", price);
		var list = query.getResultList();
		System.out.println(list);
		assertEquals(count, list.size());
	}
	
	@ParameterizedTest	@Order(6) @CsvSource({
		"Programming, 3",
		"Java, 2",
		"Spring Framework, 1",
		"Angular, 1"
	})
	void test_for_member_of(String tag, int count) {
		String qlString = "select b from Book b where :tag member of b.tags";
		var query = em.createQuery(qlString, Book.class);
		query.setParameter("tag", tag);
		var list = query.getResultList();
		
		assertEquals(count, list.size());
	}
	
	@Test
	@Order(5)
	void test_for_jpql_treat() {
		String qlJoinString = "select o from Order o join TREAT(o.product as Book) b where b.author = :author";
		
		var query = em.createQuery(qlJoinString, com.jdc.join.model.entity.treat.Order.class);
		query.setParameter("author", "Josh Long");
		var result = query.getSingleResult();
		assertNotNull(result);
		assertEquals(1, result.getId());
		
	}
	
	@ParameterizedTest
	@Order(4)
	@CsvSource({
		"Yangon, 1",
		"Mandalay, 1",
		"Sagaing, 0"
	})
	void test_for_jpql_join_with_creteria(String address, int count) {
		String qlJoinString = "select reg from Registration reg where reg.student.contact.address = :address";
		
		var query = em.createQuery(qlJoinString, Registration.class);
		query.setParameter("address", address);
		
		var list = query.getResultList();
		assertEquals(count, list.size());
	}
	
	@Test
	@Order(3)
	void test_for_jpql_join() {
		String qlJoinString = "select reg from Registration reg join reg.student";
		
		var query = em.createQuery(qlJoinString, Registration.class);
		
		var list = query.getResultList();
		assertEquals(2, list.size());
	}
	
	@Test
	@Disabled
	void test_for_fetch_join() {
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
		
		var book1 = new Book();
		book1.setName("Reactive spring");
		book1.setPrice(40000);
		book1.setAuthor("Josh Long");
		book1.setTags(List.of("Programming", "Java", "Spring Framework"));
		book1.setWholesalePrice(Map.of(
				WholesaleType.Agent, 35000.0,
				WholesaleType.Company, 36000.0,
				WholesaleType.Store, 37000.0
				));
		
		var book2 = new Book();
		book2.setName("OCP Exam Guide Java 21");
		book2.setPrice(420000);
		book2.setAuthor("Jenny");
		book2.setTags(List.of("Programming", "Java", "Java SE"));
		book2.setWholesalePrice(Map.of(
				WholesaleType.Agent, 35000.0,
				WholesaleType.Company, 36000.0,
				WholesaleType.Store, 38000.0
				));
		
		var book3 = new Book();
		book3.setName("Angular Ninja");
		book3.setPrice(60000);
		book3.setAuthor("Google Team");
		book3.setTags(List.of("Programming", "Javascript", "Typescript", "Angular"));
		
		var order1 = new com.jdc.join.model.entity.treat.Order();
		order1.setProduct(book1);
		order1.setOrderTime(LocalDateTime.now());
		order1.setQuantity(2);
		order1.setUnitPrice(book1.getPrice());
		
		var order2 = new com.jdc.join.model.entity.treat.Order();
		order2.setProduct(book3);
		order2.setOrderTime(LocalDateTime.now());
		order2.setQuantity(3);
		order2.setUnitPrice(book2.getPrice());
		
		em.getTransaction().begin();
		
		em.persist(course);
		em.persist(course2);
		em.persist(course3);
		
		em.persist(stu1);
		em.persist(stu2);
		em.persist(stu3);
		
		em.persist(reg1);
		em.persist(reg2);
		
		em.persist(book1);
		em.persist(book2);
		em.persist(book3);
		
		em.persist(order1);
		em.persist(order2);
		
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
