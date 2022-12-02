package it.university.student.repository.student;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest @TestMethodOrder(OrderAnnotation.class)
public class TestStudentRepository {
	
	@Autowired private StudentRepository repository;
	
	public void testFindStudents() {
		final List<Student> students = this.repository.findAll();
		assertEquals(students.size(), 0);
	}
	
	public void testFindStudentById() {
		final String id = "AB123CD";
		assertNull(this.repository.findById(id));
	}
	
	public void testFindStudentsByDateOfBirth() {
		final LocalDate startDate = LocalDate.parse("1990-01-01");
		final LocalDate endDate   = LocalDate.now();
		final List<Student> students = this.repository.findAllByDate(startDate, endDate);
		assertEquals(students.size(), 0);
	}
	
	public void testFindStudentsBySexMale() {
		final char sex = 'M';
		final List<Student> students = this.repository.findAllBySex(sex);
		assertEquals(students.size(), 0);
	}
	
	public void testFindStudentsBySexFemale() {
		final char sex = 'F';
		final List<Student> students = this.repository.findAllBySex(sex);
		assertEquals(students.size(), 0);
	}
	
	public void testFindStudentByBachelorDegree() {
		final List<Student> students = this.repository.findAllByBachelorDegree();
		assertEquals(students.size(), 0);
	}
	
	public void testFindStudentsByDiplomaGrade() {
		final byte diplomaGrade = 60;
		final List<Student> students = this.repository.findAllByDiplomaGrade(diplomaGrade);
		assertEquals(students.size(), 0);		
	}
	
	public void testFindStudentsByBachelorGrade() {
		final byte bacherlorGrade = 100;
		final List<Student> students = this.repository.findAllByBachelorGrade(bacherlorGrade);
		assertEquals(students.size(), 0);		
	}
}
