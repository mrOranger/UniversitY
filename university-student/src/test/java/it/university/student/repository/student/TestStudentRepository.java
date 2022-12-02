package it.university.student.repository.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.university.student.dao.impl.StudentService;
import it.university.student.entity.Address;
import it.university.student.entity.Course;
import it.university.student.entity.Department;
import it.university.student.entity.Exam;
import it.university.student.entity.Faculty;
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
	
	@Test @Order(9)
	public void testFindStudentsByCity() {
		final String city = "Roma";
		final List<Student> students = this.service.findAllByCity(city);
		assertEquals(students.size(), 0);
	}
	
	@Test @Order(10)
	public void testFindStudentsByProvince() {
		final String province = "Roma";
		final List<Student> students = this.service.findAllByProvince(province);
		assertEquals(students.size(), 0);
	}
	
	@Test @Order(11)
	public void testFindStudentsByRegion() {
		final String region = "Lazio";
		final List<Student> students = this.service.findAllByRegion(region);
		assertEquals(students.size(), 0);
	}
	
	@Test @Order(12)
	public void testFindStudentsByNation() {
		final String nation = "Italia";
		final List<Student> students = this.service.findAllByNation(nation);
		assertEquals(students.size(), 0);
	}
	
	@Test @Order(13)
	public void testFindStudentsByAddress() {
		
		final Address address = new Address();
		address.setId(1);
		address.setStreet("Via Nazionale");
		address.setNumber(123);
		address.setCity("Milano");
		address.setProvince("Milano");
		address.setRegion("Lombardia");
		address.setNation("Italia");
		
		final List<Student> students = this.service.findAllByAddress(address);
		assertEquals(students.size(), 0);
	}
	
	@Test @Order(14)
	public void testFindStudentsByDepartment() {
		
		final Department department = new Department();
		department.setName("Dipartimento 1");
		
		final List<Student> students = this.service.findAllByDepartment(department);
		assertEquals(students.size(), 0);		
	}
	
	@Test @Order(15)
	public void testFindStudentsByFaculty() {
		
		final Faculty faculty = new Faculty();
		faculty.setName("Facoltà 1");
		
		final List<Student> students = this.service.findAllByFaculty(faculty);
		assertEquals(students.size(), 0);		
	}
	
	@Test @Order(16)
	public void testFindStudentsByExam() {
		
		final Exam exam = new Exam();
		exam.setId(1);
		exam.setDate(Date.valueOf(LocalDate.now()));
		
		final Course course = new Course();
		course.setId(1);
		course.setName("Analisi 1");
		
		exam.setCourse(course);
		
		final List<Student> students = this.service.findAllByExam(exam);
		assertEquals(students.size(), 0);		
		
	}
	
	@Test @Order(17)
	public void testFindStudentsByExamAbsent() {
		final Exam exam = new Exam();
		exam.setId(1);
		exam.setDate(Date.valueOf(LocalDate.now()));
		
		final Course course = new Course();
		course.setId(1);
		course.setName("Analisi 1");
		
		exam.setCourse(course);
		
		final List<Student> students = this.service.findAllByExamAbsent(exam);
		assertEquals(students.size(), 0);		
	}
	
	@Test @Order(18)
	public void testFindStudentsByExamVote() {
		final Exam exam = new Exam();
		exam.setId(1);
		exam.setVote((byte)28);
		exam.setDate(Date.valueOf(LocalDate.now()));
		
		final Course course = new Course();
		course.setId(1);
		course.setName("Analisi 1");
		
		exam.setCourse(course);
		
		final List<Student> students = this.service.findAllByExamVote(exam);
		assertEquals(students.size(), 0);		
	}
	
	@Test @Order(18)
	public void testFindStudentsByExamPassed() {
		final Exam exam = new Exam();
		exam.setId(1);
		exam.setDate(Date.valueOf(LocalDate.now()));
		
		final Course course = new Course();
		course.setId(1);
		course.setName("Analisi 1");
		
		exam.setCourse(course);
		
		final List<Student> students = this.service.findAllByExamPassed(exam);
		assertEquals(students.size(), 0);		
	}
	
	@Test @Order(19)
	public void testFindStudentsByExamNotPassed() {
		final Exam exam = new Exam();
		exam.setId(1);
		exam.setDate(Date.valueOf(LocalDate.now()));
		
		final Course course = new Course();
		course.setId(1);
		course.setName("Analisi 1");
		
		exam.setCourse(course);
		
		final List<Student> students = this.service.findAllByExamNotPassed(exam);
		assertEquals(students.size(), 0);		
	}
}
