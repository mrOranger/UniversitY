package it.university.department.repository.student;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest @TestMethodOrder(OrderAnnotation.class) @Order(11)
public class UpdateTest {
	
	@Test @Order(1)
	public void test() {
		System.out.println("Test Update");
	}
}
