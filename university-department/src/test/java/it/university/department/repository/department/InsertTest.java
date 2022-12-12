package it.university.department.repository.department;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.university.department.Setup;
import it.university.department.dao.impl.DepartmentService;

@SpringBootTest @TestMethodOrder(OrderAnnotation.class) @Order(2)
public class InsertTest implements Setup{
	
	@Autowired private DepartmentService departmentService;
	
	@Override
	public void setup() {
		
	}

	public void addDepartment() {
		
	}
	
	public void addDepartmentWithFailure() {
		
	}
}
