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
import it.university.student.entity.Student;
import it.university.student.entity.builder.AddressBuilder;
import it.university.student.entity.builder.StudentBuilder;

@SpringBootTest @TestMethodOrder(OrderAnnotation.class)
public class TestStudentRepository {
	
	@Autowired private StudentService service;
	
	@Autowired private AddressBuilder addressBuilder;
	@Autowired private StudentBuilder studentBuilder;
	
	@Test @Order(1)
	public void testFindStudents() {
		final List<StudentDTO> students = this.service.findAll();
		assertEquals(students.size(), 10);
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
		assertEquals(students.size(), 10);
	}
	
	@Test @Order(4)
	public void testFindStudentByBachelorDegree() {
		final List<StudentDTO> students = this.service.findAllByBachelorDegree();
		assertEquals(students.size(), 7);
	}
	
	@Test @Order(5)
	public void testFindStudentsByDiplomaGrade() {
		final List<StudentDTO> students = this.service.findAllByDiplomaGrade(60);
		assertEquals(students.size(), 0);		
	}
	
	@Test @Order(6)
	public void testFindStudentsByBachelorGrade() {
		final List<StudentDTO> students = this.service.findAllByBachelorGrade(100);
		assertEquals(students.size(), 4);		
	}

	@Test @Order(7)
	public void testFindStudentsByCity() {
		final String city = "Roma";
		final List<StudentDTO> students = this.service.findAllByCity(city);
		assertEquals(students.size(), 0);
	}
	
	@Test @Order(8)
	public void testFindStudentsByProvince() {
		final String province = "Roma";
		final List<StudentDTO> students = this.service.findAllByProvince(province);
		assertEquals(students.size(), 0);
	}
	
	@Test @Order(9)
	public void testFindStudentsByRegion() {
		final String region = "Lazio";
		final List<StudentDTO> students = this.service.findAllByRegion(region);
		assertEquals(students.size(), 0);
	}
	
	@Test @Order(10)
	public void testFindStudentsByNation() {
		final String nation = "Italia";
		final List<StudentDTO> students = this.service.findAllByNation(nation);
		assertEquals(students.size(), 5);
	}
	
	@Test @Order(11)
	public void testFindStudentsByAddress() {
		final List<StudentDTO> students = this.service.findAllByAddress(10);
		assertEquals(students.size(), 0);
	}
	
	@Test @Order(12)
	public void testSaveStudent() {
		
		final Address address = this.addressBuilder
				.setStreet("Via Nazionale")
				.setNumber(123)
				.setCity("Milano")
				.setProvince("Milano")
				.setRegion("Lombardia")
				.setNation("Italia")
				.build();
		
		final Student student = this.studentBuilder
				.setId("AB123CD")
				.setName("Mario")
				.setSurname("Rossi")
				.setDateOfBirth(Date.valueOf(LocalDate.parse("1990-01-01")))
				.setDiplomaGrade(79)
				.setBachelorGrade(105)
				.setAddress(address)
				.build();
		
		this.service.save(student);
		
		assertThat(this.service.findById(student.getId()))
			.extracting(StudentDTO::getId)
			.isEqualTo(student.getId());
	}
	
	@Test @Order(13)
	public void testUpdateStudent() {
		
		final Address address = this.addressBuilder
				.setStreet("Via Nazionale")
				.setNumber(123)
				.setCity("Milano")
				.setProvince("Milano")
				.setRegion("Lombardia")
				.setNation("Italia")
				.build();
		
		final Student student = this.studentBuilder
				.setId("AB123CD")
				.setName("Mario")
				.setSurname("Rossi")
				.setDateOfBirth(Date.valueOf(LocalDate.parse("1991-01-01")))
				.setDiplomaGrade(79)
				.setBachelorGrade(105)
				.setAddress(address)
				.build();
		
		this.service.save(student);
		
		assertThat(this.service.findById(student.getId()))
			.extracting(StudentDTO::getId)
			.isEqualTo(student.getId());
	}
	
	@Test @Order(14)
	public void testDeleteStudent() {
		this.service.deleteStudent("AB123CD");
		assertNull(this.service.findById("AB123CD"));
	}
	
	@Test @Order(15)
	public void testFindStudentsFinal() {
		final List<StudentDTO> students = this.service.findAll();
		assertEquals(students.size(), 10);
	}
}
