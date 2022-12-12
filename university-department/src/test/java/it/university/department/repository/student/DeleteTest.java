package it.university.department.repository.student;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.university.department.dao.impl.StudentService;

@SpringBootTest @TestMethodOrder(OrderAnnotation.class) @Order(12)
public class DeleteTest {
	
	@Autowired private StudentService studentService;
	
	@Test @Order(1)
	public void test() {
		System.out.println("Test Delete");
	}
}
