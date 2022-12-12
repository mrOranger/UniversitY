package it.university.department.repository.professor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.university.department.dao.impl.ProfessorService;
import it.university.department.dto.ProfessorDTO;

@SpringBootTest @TestMethodOrder(OrderAnnotation.class) @Order(5)
public class SelectTest {
	
	@Autowired private ProfessorService professorService;
	
	@Test @Order(1)
	public void testFindDirector() {
		final List<ProfessorDTO> director = this.professorService.findDirectorsByDepartment("Ingegneria Informatica");
		assertThat(director)
			.extracting(ProfessorDTO::getId)
			.isEqualTo("123AB");
	}
	
	@Test @Order(2)
	public void testFindDirectorFail() {
		final List<ProfessorDTO> directors = this.professorService.findDirectorsByDepartment("Medicina");
		assertEquals(directors, 0);
	}
	
	@Test @Order(1)
	public void testFindProfessors() {
		final List<ProfessorDTO> professors = this.professorService.findAllByDepartment("Ingegneria Informatica");
		assertEquals(professors.size(), 1);
	}
	
	@Test @Order(2)
	public void testFindProfessorsFail() {
		final List<ProfessorDTO> professors = this.professorService.findAllByDepartment("Medicina");
		assertEquals(professors.size(), 0);
	}
}
