package it.university.department.repository.professor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.assertj.core.util.Lists;
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
	public void testFindDirectors() {
		final List<ProfessorDTO> director = this.professorService.findDirectorsByDepartment("Ingegneria Informatica");
		assertThat(director)
			.extracting(ProfessorDTO::getId)
			.isEqualTo(Lists.newArrayList("123AB", "123EF"));
	}
	
	@Test @Order(2)
	public void testFindDirectorsFail() {
		final List<ProfessorDTO> directors = this.professorService.findDirectorsByDepartment("Medicina");
		assertEquals(directors.size(), 0);
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
