package it.university.student.repository.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.university.student.dao.impl.StudentService;
import it.university.student.entity.Student;

@SpringBootTest @TestMethodOrder(OrderAnnotation.class)
public class TestStudentRepository {
	
	@Autowired private StudentService service;
	
	@Test @Order(1)
	public void testFindStudents() {
		final List<Student> students = this.service.findAll();
		assertEquals(students.size(), 0);
	}
	
	@Test @Order(2)
	public void testFindStudentById() {
		final String id = "AB123CD";
		assertNull(this.service.findById(id));
	}
	
	@Test @Order(3)
	public void testFindStudentsByDateOfBirth() {
		final LocalDate startDate = LocalDate.parse("1990-01-01");
		final LocalDate endDate   = LocalDate.now();
		final List<Student> students = this.service.findAllByDate(startDate, endDate);
		assertEquals(students.size(), 0);
	}
	
	@Test @Order(4)
	public void testFindStudentsBySexMale() {
		final char sex = 'M';
		final List<Student> students = this.service.findAllBySex(sex);
		assertEquals(students.size(), 0);
	}
	
	@Test @Order(5)
	public void testFindStudentsBySexFemale() {
		final char sex = 'F';
		final List<Student> students = this.service.findAllBySex(sex);
		assertEquals(students.size(), 0);
	}
	
	@Test @Order(6)
	public void testFindStudentByBachelorDegree() {
		final List<Student> students = this.service.findAllByBachelorDegree();
		assertEquals(students.size(), 0);
	}
	
	@Test @Order(7)
	public void testFindStudentsByDiplomaGrade() {
		final byte diplomaGrade = 60;
		final List<Student> students = this.service.findAllByDiplomaGrade(diplomaGrade);
		assertEquals(students.size(), 0);		
	}
	
	@Test @Order(8)
	public void testFindStudentsByBachelorGrade() {
		final byte bacherlorGrade = 100;
		final List<Student> students = this.service.findAllByBachelorGrade(bacherlorGrade);
		assertEquals(students.size(), 0);		
	}
}
