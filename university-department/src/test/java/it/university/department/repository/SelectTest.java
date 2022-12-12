package it.university.department.repository;

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

import it.university.department.entity.Department;
import it.university.department.entity.Faculty;
import it.university.department.entity.Professor;
import it.university.department.entity.Student;

@SpringBootTest @TestMethodOrder(OrderAnnotation.class) @Order(1)
public class SelectTest {
	
	@Autowired private DepartmentService departmentService;
	@Autowired private StudentService studentService;
	@Autowired private ProfessorService professorService;
	
	@Autowired private DepartmentBuilder departmentBuilder;
	@Autowired private AddressBuilder addressBuilder;
	@Autowired private FacultyBuilder facultyBuilder;
	@Autowired private StudentBuilder studentBuilder;

	@Test @Order(1)
	public void testfindDepartments() {
		final List<Department> departments = this.departmentService.findAll();
		assertEquals(departments.size(), 8);
	}
	
	@Test @Order(2)
	public void testFindDepartmentByName() {
		
		final Address address = this.addressBuilder
				.setStreet("Via Nazionale")
				.setNumber(123)
				.setCity("Milano")
				.setProvince("Milano")
				.setRegion("Lombardia")
				.setNation("Italia")
				.build();
		
		final Faculty faculty = this.facultyBuilder
				.setName("Ingegneria")
				.setAddress(address)
				.build();
		
		final Department department = this.departmentService.findById("Ingegneria Informatica");
		assertThat(department)
			.extracting(Department::getName)
			.isEqualTo("Ingegneria Informatica");
		
		assertThat(department)
			.extracting(Department::getAddress)
			.isEqualTo(address);
		
		assertThat(department)
			.extracting(Department::getFaculty)
			.isEqualTo(faculty);
	}
	
	@Test @Order(3)
	public void testFindDepartmentByNameFail() {
		final Department department = this.departmentService.findById("Ingegneria Matematica");
		assertNull(department);
	}
	
	@Test @Order(4)
	public void testFindDepartmentsByAddress() {
		final List<Department> departments = this.departmentService.findAllByAddress(1);
		assertEquals(departments.size(), 1);
	}
	
	@Test @Order(5)
	public void testFindDepartmentsByAddressStreet() {
		final List<Department> departments = this.departmentService.findAllByAddressStreet("Via Nazionale");
		assertEquals(departments.size(), 4);
	}
	
	@Test @Order(6)
	public void testFindDepartmentsByAddressCity() {
		final List<Department> departments = this.departmentService.findAllByAddressCity("Milano");
		assertEquals(departments.size(), 1);
	}
	
	@Test @Order(7)
	public void testFindDepartmentsByAddressProvince() {
		final List<Department> departments = this.departmentService.findAllByAddressProvince("Milano");
		assertEquals(departments.size(), 1);
	}
	
	@Test @Order(8)
	public void testFindDepartmentsByAddressRegion() {
		final List<Department> departments = this.departmentService.findAllByAddressRegion("Lombardia");
		assertEquals(departments.size(), 1);
	}
	
	@Test @Order(9)
	public void testFindDepartmentsByAddressNation() {
		final List<Department> departments = this.departmentService.findAllByAddressStreet("Italia");
		assertEquals(departments.size(), 4);
	}
	
	@Test @Order(10)
	public void testFindDepartmentsByAddressFail() {
		final List<Department> departments = this.departmentService.findAllByAddress(10);
		assertEquals(departments.size(), 0);
	}
	
	@Test @Order(11)
	public void testFindDepartmentsByFaculty() {
		final List<Department> departments = this.departmentService.findAllByFaculty("Ingegneria");
		assertEquals(departments.size(), 3);
	}
	
	@Test @Order(12)
	public void testFindDepartmentsByFacultyFail() {
		final List<Department> departments = this.departmentService.findAllByFaculty("Medicina");
		assertEquals(departments.size(), 0);
	}
	
	@Test @Order(13)
	public void testFindStudentsByDepartment() {
		final List<Student> students = this.studentService.findAllByDepartment("Ingegneria Informatica");
		assertEquals(students.size(), 2);
		
		assertThat(students.get(0))
			.extracting(Student::getId)
			.isEqualTo("AB123");
		
		assertThat(students.get(1))
			.extracting(Student::getId)	
			.isEqualTo("CD123");
	}
	
	@Test @Order(14)
	public void testFindStudentsByDepartmentFail() {
		final List<Student> students = this.studentService.findAllByDepartment("Medicia");
		assertEquals(students.size(), 0);
	}
	
	@Test @Order(15)
	public void testFindDirector() {
		final Professor director = this.departmentService.findByDepartment("Ingegneria Informatica");
		assertThat(director)
			.extracting(Professor::getId)
			.isEqualTo("123AB");
	}
	
	@Test @Order(16)
	public void testFindDirectorFail() {
		final Professor director = this.departmentService.findByDepartment("Medicina");
		assertNull(director);
	}
}
