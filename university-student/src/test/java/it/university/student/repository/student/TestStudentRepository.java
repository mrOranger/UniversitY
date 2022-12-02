package it.university.student.repository.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import it.university.student.entity.Professor;
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
	public void testFindStudentsByExamPresent() {
		final Exam exam = new Exam();
		exam.setId(1);
		exam.setDate(Date.valueOf(LocalDate.now()));
		
		final Course course = new Course();
		course.setId(1);
		course.setName("Analisi 1");
		
		exam.setCourse(course);
		
		final List<Student> students = this.service.findAllByExamPresent(exam);
		assertEquals(students.size(), 0);		
	}
	
	@Test @Order(18)
	public void testFindStudentsByExamAbsent() {
		final Exam exam = new Exam();
		exam.setId(1);
		exam.setDate(Date.valueOf(LocalDate.now()));
		
		final Course course = new Course();
		course.setId(1);
		course.setName("Analisi 1");
		
		exam.setCourse(course);
		
		final List<Student> students = this.service.findAllByExamAbsente(exam);
		assertEquals(students.size(), 0);		
	}
	
	@Test @Order(19)
	public void testFindStudentsByExamVote() {
		final Exam exam = new Exam();
		exam.setId(1);
		exam.setVote((byte)28);
		exam.setDate(Date.valueOf(LocalDate.now()));
		
		final Course course = new Course();
		course.setId(1);
		course.setName("Analisi 1");
		
		exam.setCourse(course);
		
		final List<Student> students = this.service.findAllByExam(exam);
		assertEquals(students.size(), 0);		
	}
	
	@Test @Order(20)
	public void testFindStudentsByExamPassed() {
		final Exam exam = new Exam();
		exam.setId(1);
		exam.setDate(Date.valueOf(LocalDate.now()));
		
		final Course course = new Course();
		course.setId(1);
		course.setName("Analisi 1");
		
		exam.setCourse(course);
		
		final List<Student> students = this.service.findAllByExamVoteGreaterThan(exam, (byte)18);
		assertEquals(students.size(), 0);		
	}
	
	@Test @Order(21)
	public void testFindStudentsByExamNotPassed() {
		final Exam exam = new Exam();
		exam.setId(1);
		exam.setDate(Date.valueOf(LocalDate.now()));
		
		final Course course = new Course();
		course.setId(1);
		course.setName("Analisi 1");
		
		exam.setCourse(course);
		
		final List<Student> students = this.service.findAllByExamVoteLowerThan(exam, (byte)18);
		assertEquals(students.size(), 0);
	}
	
	public void testSaveStudent() {
		
		final Student student = new Student();
		student.setId("AB123CD");
		student.setName("Mario");
		student.setSurname("Rossi");
		student.setSex('M');
		student.setDateOfBirth(Date.valueOf(LocalDate.parse("1990-01-01")));
		student.setDiplomaGrade((byte)79);
		student.setBachelorGrade((byte)105);
		
		final Address address = new Address();
		address.setId(1);
		address.setStreet("Via Nazionale");
		address.setNumber(123);
		address.setCity("Milano");
		address.setProvince("Milano");
		address.setRegion("Lombardia");
		address.setNation("Italia");
		
		student.setAddress(address);
		
		final Department department = new Department();
		department.setName("Dipartimento 1");
		department.setAddress(address);
		
		final Professor professor = new Professor();
		professor.setId("XK123JH");
		professor.setName("Maria");
		professor.setSurname("Rossi");
		professor.setDepartment(department);
		
		department.setDirector(professor);
		
		final Faculty faculty = new Faculty();
		faculty.setName("Facoltà 1");
		faculty.setDirector(professor);
		
		department.setFaculty(faculty);
		
		Set<Professor> professors = new HashSet<>();
		professors.add(professor);
		
		final Course course = new Course();
		course.setId(1);
		course.setName("Analisi 1");
		course.setDate(Date.valueOf(LocalDate.now()));
		course.setProfessors(professors);
		
		final Exam exam = new Exam();
		exam.setId(1);
		exam.setDate(Date.valueOf(LocalDate.parse("2022-01-01")));
		exam.setVote((byte)21);
		exam.setCourse(course);
		
		Set<Exam> exams = new HashSet<>();
		exams.add(exam);
		
		student.setExams(exams);
		
		this.service.save(student);
	}
	
	public void testSaveStudentExamAbsent() {
		
		final Student student = new Student();
		student.setId("AB456CD");
		student.setName("Federica");
		student.setSurname("Gialli");
		student.setSex('F');
		student.setDateOfBirth(Date.valueOf(LocalDate.parse("1995-01-01")));
		student.setDiplomaGrade((byte)100);
		
		final Address address = new Address();
		address.setId(2);
		address.setStreet("Via Nazionale");
		address.setNumber(123);
		address.setCity("Torino");
		address.setProvince("Torino");
		address.setRegion("Piemonte");
		address.setNation("Italia");
		
		student.setAddress(address);
		
		final Department department = new Department();
		department.setName("Dipartimento 1");
		department.setAddress(address);
		
		final Professor professor = new Professor();
		professor.setName("Maria");
		professor.setSurname("Rossi");
		professor.setDepartment(department);
		
		department.setDirector(professor);
		
		final Faculty faculty = new Faculty();
		faculty.setName("Facoltà 1");
		faculty.setDirector(professor);
		
		department.setFaculty(faculty);
		
		Set<Professor> professors = new HashSet<>();
		professors.add(professor);
		
		final Course course = new Course();
		course.setName("Analisi 1");
		course.setProfessors(professors);
		
		final Exam exam = new Exam();
		exam.setDate(Date.valueOf(LocalDate.parse("2022-01-01")));
		exam.setCourse(course);
		
		Set<Exam> exams = new HashSet<>();
		exams.add(exam);
		
		student.setExams(exams);
		
		this.service.save(student);
	}
	
	public void testSaveStudentExamPassed() {
		
		final Student student = new Student();
		student.setId("AB456KJ");
		student.setName("John");
		student.setSurname("Black");
		student.setSex('M');
		student.setDateOfBirth(Date.valueOf(LocalDate.parse("1997-01-01")));
		student.setDiplomaGrade((byte)100);
		
		final Address address = new Address();
		address.setId(3);
		address.setStreet("Groove Street");
		address.setNumber(123);
		address.setCity("Los Angeles");
		address.setProvince("Los Angeles");
		address.setRegion("California");
		address.setNation("USA");
		
		student.setAddress(address);
		
		final Department department = new Department();
		department.setName("Dipartimento 2");
		department.setAddress(address);
		
		final Professor professor = new Professor();
		professor.setId("OP321PP");
		professor.setName("Federico");
		professor.setSurname("Gialli");
		professor.setDepartment(department);
		
		department.setDirector(professor);
		
		final Faculty faculty = new Faculty();
		faculty.setName("Facoltà 2");
		faculty.setDirector(professor);
		
		department.setFaculty(faculty);
		
		Set<Professor> professors = new HashSet<>();
		professors.add(professor);
		
		final Course course = new Course();
		course.setName("Programmazione");
		course.setProfessors(professors);
		
		final Exam exam = new Exam();
		exam.setDate(Date.valueOf(LocalDate.parse("2022-01-01")));
		exam.setVote((byte)30);
		exam.setCourse(course);
		
		Set<Exam> exams = new HashSet<>();
		exams.add(exam);
		
		student.setExams(exams);
		
		this.service.save(student);
	}
	
	public void testSaveStudentExamNotPassed() {
		
		final Student student = new Student();
		student.setId("AB456KJ");
		student.setName("John");
		student.setSurname("Black");
		student.setSex('M');
		student.setDateOfBirth(Date.valueOf(LocalDate.parse("1997-01-01")));
		student.setDiplomaGrade((byte)100);
		
		final Address address = new Address();
		address.setStreet("Groove Street");
		address.setNumber(123);
		address.setCity("Los Angeles");
		address.setProvince("Los Angeles");
		address.setRegion("California");
		address.setNation("USA");
		
		student.setAddress(address);
		
		final Department department = new Department();
		department.setName("Dipartimento 2");
		department.setAddress(address);
		
		final Professor professor = new Professor();
		professor.setName("Federico");
		professor.setSurname("Gialli");
		professor.setDepartment(department);
		
		department.setDirector(professor);
		
		final Faculty faculty = new Faculty();
		faculty.setName("Facoltà 2");
		faculty.setDirector(professor);
		
		department.setFaculty(faculty);
		
		Set<Professor> professors = new HashSet<>();
		professors.add(professor);
		
		final Course course = new Course();
		course.setName("Programmazione");
		course.setProfessors(professors);
		
		final Exam exam = new Exam();
		exam.setDate(Date.valueOf(LocalDate.parse("2022-01-01")));
		exam.setVote((byte)15);
		exam.setCourse(course);
		
		Set<Exam> exams = new HashSet<>();
		exams.add(exam);
		
		student.setExams(exams);
		
		this.service.save(student);		
	}
}
