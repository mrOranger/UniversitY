package it.university.department.repository.professor;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.university.department.dao.impl.ProfessorService;

@SpringBootTest @TestMethodOrder(OrderAnnotation.class) @Order(8)
public class DeleteTest {
	
	@Autowired private ProfessorService professorService;
	
	@Test @Order(1)
	public void test() {
		System.out.println("Test Delete");
	}
}
