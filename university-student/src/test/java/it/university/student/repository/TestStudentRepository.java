package it.university.student.repository;

import static org.assertj.core.api.Assertions.assertThat;
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
import it.university.student.dto.StudentDTO;
import it.university.student.entity.Address;
import it.university.student.entity.Course;
import it.university.student.entity.Department;
import it.university.student.entity.Exam;
import it.university.student.entity.Faculty;
import it.university.student.entity.Professor;
import it.university.student.entity.Student;
import it.university.student.entity.builder.AddressBuilder;
import it.university.student.entity.builder.CourseBuilder;
import it.university.student.entity.builder.DepartmentBuilder;
import it.university.student.entity.builder.ExamBuilder;
import it.university.student.entity.builder.FacultyBuilder;
import it.university.student.entity.builder.ProfessorBuilder;
import it.university.student.entity.builder.StudentBuilder;

@SpringBootTest @TestMethodOrder(OrderAnnotation.class)
public class TestStudentRepository {
	
	@Autowired private StudentService service;
	
	@Autowired private AddressBuilder addressBuilder;
	@Autowired private CourseBuilder courseBuilder;
	@Autowired private DepartmentBuilder departmentBuilder;
	@Autowired private ExamBuilder examBuilder;
	@Autowired private FacultyBuilder facultyBuilder;
	@Autowired private ProfessorBuilder professorBuilder;
	@Autowired private StudentBuilder studentBuilder;
	
	@Test @Order(1)
	public void testFindStudents() {
		final List<StudentDTO> students = this.service.findAll();
		assertEquals(students.size(), 0);
	}
	
	@Test @Order(2)
	public void testFindStudentById() {
		final String id = "AB123CD";
		assertNull(this.service.findById(id));
	}
	
	@Test @Order(3)
	public void testFindStudentsByDateOfBirth() {
		final Date startDate = Date.valueOf(LocalDate.parse("1990-01-01"));
		final Date endDate   = Date.valueOf(LocalDate.now());
		final List<StudentDTO> students = this.service.findAllByDate(startDate, endDate);
		assertEquals(students.size(), 0);
	}
	
	@Test @Order(4)
	public void testFindStudentsBySexMale() {
		final char sex = 'M';
		final List<StudentDTO> students = this.service.findAllBySex(sex);
		assertEquals(students.size(), 0);
	}
	
	@Test @Order(5)
	public void testFindStudentsBySexFemale() {
		final char sex = 'F';
		final List<StudentDTO> students = this.service.findAllBySex(sex);
		assertEquals(students.size(), 0);
	}
	
	@Test @Order(6)
	public void testFindStudentByBachelorDegree() {
		final List<StudentDTO> students = this.service.findAllByBachelorDegree();
		assertEquals(students.size(), 0);
	}
	
	@Test @Order(7)
	public void testFindStudentsByDiplomaGrade() {
		final byte diplomaGrade = 60;
		final List<StudentDTO> students = this.service.findAllByDiplomaGrade(diplomaGrade);
		assertEquals(students.size(), 0);		
	}
	
	@Test @Order(8)
	public void testFindStudentsByBachelorGrade() {
		final byte bacherlorGrade = 100;
		final List<StudentDTO> students = this.service.findAllByBachelorGrade(bacherlorGrade);
		assertEquals(students.size(), 0);		
	}

	@Test @Order(9)
	public void testFindStudentsByCity() {
		final String city = "Roma";
		final List<StudentDTO> students = this.service.findAllByCity(city);
		assertEquals(students.size(), 0);
	}
	
	@Test @Order(10)
	public void testFindStudentsByProvince() {
		final String province = "Roma";
		final List<StudentDTO> students = this.service.findAllByProvince(province);
		assertEquals(students.size(), 0);
	}
	
	@Test @Order(11)
	public void testFindStudentsByRegion() {
		final String region = "Lazio";
		final List<StudentDTO> students = this.service.findAllByRegion(region);
		assertEquals(students.size(), 0);
	}
	
	@Test @Order(12)
	public void testFindStudentsByNation() {
		final String nation = "Italia";
		final List<StudentDTO> students = this.service.findAllByNation(nation);
		assertEquals(students.size(), 0);
	}
	
	@Test @Order(13)
	public void testFindStudentsByAddress() {
		final List<StudentDTO> students = this.service.findAllByAddress(1);
		assertEquals(students.size(), 0);
	}
	
	@Test @Order(14)
	public void testFindStudentsByDepartment() {
		final List<StudentDTO> students = this.service.findAllByDepartment("Dipartimento 1");
		assertEquals(students.size(), 0);		
	}
	
	@Test @Order(15)
	public void testFindStudentsByFaculty() {
		final List<StudentDTO> students = this.service.findAllByFaculty("Facoltà 1");
		assertEquals(students.size(), 0);		
	}
	
	@Test @Order(16)
	public void testFindStudentsByExam() {
		final List<StudentDTO> students = this.service.findAllByExam(1);
		assertEquals(students.size(), 0);		
		
	}
	
	@Test @Order(17)
	public void testFindStudentsByExamPresent() {
		final List<StudentDTO> students = this.service.findAllByExamPresent(1);
		assertEquals(students.size(), 0);		
	}
	
	@Test @Order(18)
	public void testFindStudentsByExamAbsent() {
		final List<StudentDTO> students = this.service.findAllByExamAbsente(1);
		assertEquals(students.size(), 0);		
	}
	
	@Test @Order(19)
	public void testFindStudentsByExamVote() {
		final List<StudentDTO> students = this.service.findAllByExam(1);
		assertEquals(students.size(), 0);		
	}
	
	@Test @Order(20)
	public void testFindStudentsByExamPassed() {
		final List<StudentDTO> students = this.service.findAllByExamVoteGreaterThan(1, (byte)18);
		assertEquals(students.size(), 0);		
	}
	
	@Test @Order(21)
	public void testFindStudentsByExamNotPassed() {
		final List<StudentDTO> students = this.service.findAllByExamVoteLowerThan(1, (byte)18);
		assertEquals(students.size(), 0);
	}
	
	@Test @Order(22)
	public void testSaveStudent() {
		
		final Address address = this.addressBuilder
				.setStreet("Via Nazionale")
				.setNumber(123)
				.setCity("Milano")
				.setProvince("Milano")
				.setRegion("Lombardia")
				.setNation("Italia")
				.build();
		
		final Faculty faculty = this.facultyBuilder
				.setName("Facoltà 1")
				.build();
		
		final Department department = this.departmentBuilder
				.setName("Dipartimento 1")
				.setFaculty(faculty)
				.build();
		
		final Professor professor = this.professorBuilder
				.setId("XK123JH")
				.setName("Maria")
				.setSurname("Rossi")
				.setDateOfBirth(Date.valueOf(LocalDate.parse("2022-01-01")))
				.build();
		
		final Course course = this.courseBuilder
				.setName("Analisi 1")
				.setDate(Date.valueOf(LocalDate.now()))
				.addProfessor(professor)
				.build();
		
		final Exam exam = this.examBuilder
				.setDate(Date.valueOf(LocalDate.parse("2022-01-01")))
				.setVote((byte)21)
				.setCourse(course)
				.build();
		
		final Student student = this.studentBuilder
				.setId("AB123CD")
				.setName("Mario")
				.setSurname("Rossi")
				.setSex('M')
				.setDateOfBirth(Date.valueOf(LocalDate.parse("1990-01-01")))
				.setDiplomaGrade((byte)79)
				.setBachelorGrade((byte)105)
				.setAddress(address)
				.setDeparment(department)
				.addExam(exam)
				.build();
		
		this.service.save(student);
		
		assertThat(this.service.findById(student.getId()))
			.extracting(StudentDTO::getId)
			.isEqualTo(student.getId());
		
	}
	
	@Test @Order(23)
	public void testSaveStudentExamAbsent() {
		
		final Address address = this.addressBuilder
				.setStreet("Dam Square")
				.setNumber(123)
				.setCity("Amsterdam")
				.setProvince("Amsterdam")
				.setRegion("Amsterdam")
				.setNation("Holland")
				.build();
		
		final Faculty faculty = this.facultyBuilder
				.setName("Facoltà 2")
				.build();
		
		final Department department = this.departmentBuilder
				.setName("Dipartimento 2")
				.setFaculty(faculty)
				.build();
		
		final Professor professor = this.professorBuilder
				.setId("KL091OK")
				.setName("Federico")
				.setSurname("Rossi")
				.setDateOfBirth(Date.valueOf(LocalDate.parse("2022-01-01")))
				.build();
		
		final Course course = this.courseBuilder
				.setName("Analisi 2")
				.setDate(Date.valueOf(LocalDate.now()))
				.addProfessor(professor)
				.build();
		
		final Exam exam = this.examBuilder
				.setDate(Date.valueOf(LocalDate.parse("2022-01-01")))
				.setCourse(course)
				.build();
		
		final Student student = this.studentBuilder
				.setId("OL019AS")
				.setName("John")
				.setSurname("Von Ghogh")
				.setSex('F')
				.setDateOfBirth(Date.valueOf(LocalDate.parse("1994-01-01")))
				.setDiplomaGrade((byte)98)
				.setAddress(address)
				.setDeparment(department)
				.addExam(exam)
				.build();
		
		this.service.save(student);
		
		assertThat(this.service.findById(student.getId()))
			.extracting(StudentDTO::getId)
			.isEqualTo(student.getId());
	}
	
	@Test @Order(24)
	public void testSaveStudentExamPassed() {
		
		final Address address = this.addressBuilder
				.setStreet("Via Rosa")
				.setNumber(12)
				.setCity("Avellino")
				.setProvince("Salerno")
				.setRegion("Campania")
				.setNation("Italia")
				.build();
		
		final Faculty faculty = this.facultyBuilder
				.setName("Facoltà 2")
				.build();
		
		final Department department = this.departmentBuilder
				.setName("Dipartimento 2")
				.setFaculty(faculty)
				.build();
		
		final Professor professor = this.professorBuilder
				.setId("KL091OK")
				.setName("Federico")
				.setSurname("Rossi")
				.setDateOfBirth(Date.valueOf(LocalDate.parse("2022-01-01")))
				.build();
		
		final Course course = this.courseBuilder
				.setName("Algoritmi e Strutture Dati")
				.setDate(Date.valueOf(LocalDate.now()))
				.addProfessor(professor)
				.build();
		
		final Exam exam = this.examBuilder
				.setDate(Date.valueOf(LocalDate.parse("2022-01-01")))
				.setVote((byte)26)
				.setCourse(course)
				.build();

		final Student student = this.studentBuilder
				.setId("OL222AS")
				.setName("Michele")
				.setSurname("De Siena")
				.setSex('M')
				.setDateOfBirth(Date.valueOf(LocalDate.parse("1994-01-01")))
				.setDiplomaGrade((byte)76)
				.setAddress(address)
				.setDeparment(department)
				.addExam(exam)
				.build();
		
		this.service.save(student);
		
		assertThat(this.service.findById(student.getId()))
			.extracting(StudentDTO::getId)
			.isEqualTo(student.getId());
	}
	
	@Test @Order(25)
	public void testSaveStudentExamNotPassed() {
		
		final Address address = this.addressBuilder
				.setStreet("Via Rosa")
				.setNumber(12)
				.setCity("Avellino")
				.setProvince("Salerno")
				.setRegion("Campania")
				.setNation("Italia")
				.build();
		
		final Faculty faculty = this.facultyBuilder
				.setName("Facoltà 2")
				.build();
		
		final Department department = this.departmentBuilder
				.setName("Dipartimento 1")
				.setFaculty(faculty)
				.build();
		
		final Professor professor = this.professorBuilder
				.setId("KL091OK")
				.setName("Federico")
				.setSurname("Rossi")
				.setDateOfBirth(Date.valueOf(LocalDate.parse("2022-01-01")))
				.build();
		
		final Course course = this.courseBuilder
				.setName("Algoritmi e Strutture Dati")
				.setDate(Date.valueOf(LocalDate.now()))
				.addProfessor(professor)
				.build();
		
		final Exam exam = this.examBuilder
				.setDate(Date.valueOf(LocalDate.parse("2022-01-01")))
				.setVote((byte)16)
				.setCourse(course)
				.build();

		final Student student = this.studentBuilder
				.setId("OL019AL")
				.setName("Federico")
				.setSurname("Di Svevia")
				.setSex('M')
				.setDateOfBirth(Date.valueOf(LocalDate.parse("1994-01-01")))
				.setDiplomaGrade((byte)98)
				.setAddress(address)
				.setDeparment(department)
				.addExam(exam)
				.build();
		
		this.service.save(student);
		
		assertThat(this.service.findById(student.getId()))
			.extracting(StudentDTO::getId)
			.isEqualTo(student.getId());
	}
	
	@Test @Order(26)
	public void testUpdateStudent() {

		final Student student = this.studentBuilder
				.setId("OL019AS")
				.setName("John")
				.setSurname("Von Ghogh")
				.setSex('M')
				.setDateOfBirth(Date.valueOf(LocalDate.parse("1994-01-01")))
				.setDiplomaGrade((byte)98)
				.build();
		
		this.service.save(student);		
		
		assertThat(this.service.findById(student.getId()))
			.extracting(StudentDTO::getDateOfBirth)
			.isEqualTo(student.getDateOfBirth());
		
		assertThat(this.service.findById(student.getId()))
			.extracting(StudentDTO::getSex)
			.isEqualTo(student.getSex());
	}
	
	@Test @Order(27)
	public void testFullDatabase() {
		final List<StudentDTO> students = this.service.findAll();
		assertEquals(students.size(), 4);		
	}
	
	@Test @Order(28)
	public void testDeleteStudent() {
		this.service.deleteStudent("OL019AS");
		assertNull(this.service.findById("OL019AS"));
	}
	
	@Test @Order(29)
	public void testDeleteStudents() {
		this.service.deleteAll();	
		assertNull(this.service.findById("OL019AS"));
	}
	
	@Test @Order(30)
	public void testEmptyDatabase() {
		final List<StudentDTO> students = this.service.findAll();
		assertEquals(students.size(), 0);		
	}
	
	@Test @Order(31)
	public void testSaveUniqueStudent() {
		
		final Address address = this.addressBuilder
				.setStreet("Via Nazionale")
				.setNumber(123)
				.setCity("Milano")
				.setProvince("Milano")
				.setRegion("Lombardia")
				.setNation("Italia")
				.build();
		
		final Faculty faculty = this.facultyBuilder
				.setName("Facoltà 1")
				.build();
		
		final Department department = this.departmentBuilder
				.setName("Dipartimento 1")
				.setFaculty(faculty)
				.build();
		
		final Professor professor = this.professorBuilder
				.setId("XK123JH")
				.setName("Maria")
				.setSurname("Rossi")
				.setDateOfBirth(Date.valueOf(LocalDate.parse("2022-01-01")))
				.build();
		
		final Course course = this.courseBuilder
				.setName("Analisi 1")
				.setDate(Date.valueOf(LocalDate.now()))
				.addProfessor(professor)
				.build();
		
		final Exam exam = this.examBuilder
				.setDate(Date.valueOf(LocalDate.parse("2022-01-01")))
				.setVote((byte)21)
				.setCourse(course)
				.build();
		
		final Student student = this.studentBuilder
				.setId("AB123CD")
				.setName("Mario")
				.setSurname("Rossi")
				.setSex('M')
				.setDateOfBirth(Date.valueOf(LocalDate.parse("1990-01-01")))
				.setDiplomaGrade((byte)79)
				.setBachelorGrade((byte)105)
				.setAddress(address)
				.setDeparment(department)
				.addExam(exam)
				.build();
		
		this.service.save(student);
		
		assertThat(this.service.findById(student.getId()))
			.extracting(StudentDTO::getId)
			.isEqualTo(student.getId());
		
	}
}
