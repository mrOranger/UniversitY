package it.university.department.controller.professor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import it.university.department.JSONResponse;
import it.university.department.Setup;
import lombok.SneakyThrows;

@SpringBootTest @TestMethodOrder(OrderAnnotation.class) @Order(15)
public final class SelectTest implements Setup {
	
	private MockMvc mockMcv;
	@Autowired private WebApplicationContext context;
	private JSONObject notFoundErrorDepartment;
	private JSONObject notFoundErrorProfessor;

	@Override @BeforeEach @SneakyThrows
	public void setup() {
		this.mockMcv = MockMvcBuilders.webAppContextSetup(context).build();
		this.notFoundErrorDepartment = JSONResponse.generateResponse(HttpStatus.NOT_FOUND.value(), "Non è stato trovato alcun Dipartimento!");
		this.notFoundErrorProfessor = JSONResponse.generateResponse(HttpStatus.NOT_FOUND.value(), "Nessun professore è iscritto a questo dipartimento!");
	}
	
	@Test @Order(1) @SneakyThrows
	public final void testSelectProfessors() {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/departments/professors/Ingegneria Meccanica")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(1)))
				.andDo(print());	
	}
	
	@Test @Order(2) @SneakyThrows
	public final void testSelectProfessorsNotFound() {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/departments/professors/Informatica")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(this.notFoundErrorProfessor.toString()))
				.andDo(print());	
	}
	
	@Test @Order(3) @SneakyThrows
	public final void testSelectProfessorsDepartmentNotFound() {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/departments/professors/Medicina")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(this.notFoundErrorDepartment.toString()))
				.andDo(print());	
	}
}
