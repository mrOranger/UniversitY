package it.university.department.repository.professor;

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
import it.university.department.repository.department.DepartmentService;

@SpringBootTest @TestMethodOrder(OrderAnnotation.class) @Order(5)
public class SelectTest {
	
	@Autowired private ProfessorService professorService;
	
	@Test @Order(1)
	public void testFindDirector() {
		final Professor director = this.professorService.findDirectorByDepartment("Ingegneria Informatica");
		assertThat(director)
			.extracting(Professor::getId)
			.isEqualTo("123AB");
	}
	
	@Test @Order(2)
	public void testFindDirectorFail() {
		final Professor director = this.professorService.findDirectorByDepartment("Medicina");
		assertNull(director);
	}
	
	@Test @Order(1)
	public void testFindProfessors() {
		final List<Professor> professors = this.professorService.findAllByDepartment("Ingegneria Informatica");
		assertEquals(professors.size(), 2);
	}
	
	@Test @Order(2)
	public void testFindProfessorsFail() {
		final List<Professor> professors = this.professorService.findAllByDepartment("Medicina");
		assertEquals(professors.size(), 2);
	}
}
