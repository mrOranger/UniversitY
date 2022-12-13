package it.university.department.repository.department;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import it.university.department.dao.impl.DepartmentService;

@SpringBootTest @TestMethodOrder(OrderAnnotation.class) @Order(4)
public class DeleteTest{
	
	@Autowired private DepartmentService departmentService;
	
	@Test @Order(1)
	public void deleteDepartment() {
		this.departmentService.delete("Informatica");
		assertNull(this.departmentService.findById("Informatica"));
	}
	
	@Test @Order(2)
	public void deleteDepartmentNullWithFailure() {
		assertThrows(InvalidDataAccessApiUsageException.class, () -> this.departmentService.findById(null));
	}
}
