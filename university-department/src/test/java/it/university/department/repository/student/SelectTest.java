package it.university.department.repository.student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.university.department.dao.impl.StudentService;
import it.university.department.dto.StudentDTO;

@SpringBootTest @TestMethodOrder(OrderAnnotation.class) @Order(9)
public class SelectTest {
	
	@Autowired private StudentService studentService;
	
	@Test @Order(1)
	public void testFindStudentsByDepartment() {
		final List<StudentDTO> students = this.studentService.findAllByDepartment("Ingegneria Informatica");
		assertEquals(students.size(), 2);
		
		assertThat(students.get(0))
			.extracting(StudentDTO::getId)
			.isEqualTo("AB123");
		
		assertThat(students.get(1))
			.extracting(StudentDTO::getId)	
			.isEqualTo("CD123");
	}
	
	@Test @Order(2)
	public void testFindStudentsByDepartmentFail() {
		final List<StudentDTO> students = this.studentService.findAllByDepartment("Medicia");
		assertEquals(students.size(), 0);
	}
}
