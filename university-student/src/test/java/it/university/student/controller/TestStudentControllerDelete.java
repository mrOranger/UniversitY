package it.university.student.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.time.LocalDate;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import it.university.student.UniversityStudentApplication;
import it.university.student.repository.StudentRepository;

@ContextConfiguration(classes = UniversityStudentApplication.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TestStudentControllerDelete {
	
	private MockMvc mockMcv;
	@Autowired private WebApplicationContext context;
	@Autowired private StudentRepository repository;
	
	@BeforeEach
	public void setup() throws JSONException, IOException {
		this.mockMcv = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test @Order(1)
	public void deleteStudent() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.delete("/api/students/delete/MK871OZ"))
			.andExpect(status().isAccepted())
			.andExpect(jsonPath("$.code").value(HttpStatus.ACCEPTED.value()))
			.andExpect(jsonPath("$.date").value(LocalDate.now().toString()))
			.andExpect(jsonPath("$.message").value("Studente eliminato con successo!"))
			.andDo(print());	
		
		assertThat(this.repository.findById("MK871OZ").isEmpty())
			.isEqualTo(true);
	}
	
	@Test @Order(2)
	public void deleteStudentErrorNotFound() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.delete("/api/students/delete/MK871OZ"))
			.andExpect(status().isNotFound())
			.andExpect(jsonPath("$.code").value(HttpStatus.NOT_FOUND.value()))
			.andExpect(jsonPath("$.date").value(LocalDate.now().toString()))
			.andExpect(jsonPath("$.message").value("Non è stato trovato alcuno studente!"))
			.andDo(print());	
		
		assertThat(this.repository.findById("MK871OZ").isEmpty())
			.isEqualTo(true);
	}
}