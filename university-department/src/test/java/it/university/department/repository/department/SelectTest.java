package it.university.department.repository.department;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.university.department.dao.impl.DepartmentService;
import it.university.department.dto.DepartmentDTO;

@SpringBootTest @TestMethodOrder(OrderAnnotation.class) @Order(1)
public class SelectTest {
	
	@Autowired private DepartmentService departmentService;

	@Test @Order(1)
	public void testfindDepartments() {
		final List<DepartmentDTO> departments = this.departmentService.findAll();
		assertEquals(departments.size(), 9);
	}
	
	@Test @Order(2)
	public void testFindDepartmentByName() {
		
		final DepartmentDTO department = this.departmentService.findById("Ingegneria Informatica");
		assertThat(department)
			.extracting(DepartmentDTO::getName)
			.isEqualTo("Ingegneria Informatica");
	}
	
	@Test @Order(3)
	public void testFindDepartmentByNameFail() {
		final DepartmentDTO department = this.departmentService.findById("Ingegneria Matematica");
		assertNull(department);
	}
	
	@Test @Order(4)
	public void testFindDepartmentsByAddress() {
		final List<DepartmentDTO> departments = this.departmentService.findAllByAddress(1);
		assertEquals(departments.size(), 0);
	}
	
	@Test @Order(5)
	public void testFindDepartmentsByAddressStreet() {
		final List<DepartmentDTO> departments = this.departmentService.findAllByAddressStreet("Via Nazionale");
		assertEquals(departments.size(), 6);
	}
	
	@Test @Order(6)
	public void testFindDepartmentsByAddressCity() {
		final List<DepartmentDTO> departments = this.departmentService.findAllByAddressCity("Milano");
		assertEquals(departments.size(), 0);
	}
	
	@Test @Order(7)
	public void testFindDepartmentsByAddressProvince() {
		final List<DepartmentDTO> departments = this.departmentService.findAllByAddressProvince("Milano");
		assertEquals(departments.size(), 0);
	}
	
	@Test @Order(8)
	public void testFindDepartmentsByAddressRegion() {
		final List<DepartmentDTO> departments = this.departmentService.findAllByAddressRegion("Lombardia");
		assertEquals(departments.size(), 0);
	}
	
	@Test @Order(9)
	public void testFindDepartmentsByAddressNation() {
		final List<DepartmentDTO> departments = this.departmentService.findAllByAddressNation("Italia");
		assertEquals(departments.size(), 6);
	}
	
	@Test @Order(10)
	public void testFindDepartmentsByAddressFail() {
		final List<DepartmentDTO> departments = this.departmentService.findAllByAddress(10);
		assertEquals(departments.size(), 0);
	}
	
	@Test @Order(11)
	public void testFindDepartmentsByFaculty() {
		final List<DepartmentDTO> departments = this.departmentService.findAllByFaculty("Ingegneria");
		assertEquals(departments.size(), 3);
	}
	
	@Test @Order(12)
	public void testFindDepartmentsByFacultyFail() {
		final List<DepartmentDTO> departments = this.departmentService.findAllByFaculty("Medicina");
		assertEquals(departments.size(), 0);
	}
}
