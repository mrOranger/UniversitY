package it.university.department.controller.faculty;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import it.university.department.Setup;

@SpringBootTest @TestMethodOrder(OrderAnnotation.class) @Order(13)
public class SelectTest implements Setup {

	@Override @BeforeEach
	public void setup() {
	}
	
	@Test @Order(1)
	public void testSelectDepartmentsByFaculty() {
		
	}
	
	@Test @Order(2)
	public void testSelectDepartmentsByFacultyNotFound() {
		
	}

}
