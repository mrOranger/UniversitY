package it.university.department.controller.department;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.BeforeEach;
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

@SpringBootTest @TestMethodOrder(OrderAnnotation.class) @Order(12)
public class DeleteTest implements Setup {
	
	private MockMvc mockMcv;
	@Autowired private WebApplicationContext context;
	
	private JSONObject okResponse;
	private JSONObject notFoundResponse;

	@Override @BeforeEach @SneakyThrows
	public void setup() {
		this.mockMcv = MockMvcBuilders.webAppContextSetup(context).build();
		
		this.okResponse = JSONResponse.generateResponse(HttpStatus.OK.value(), "Dipartimento eliminato con successo!");
		this.notFoundResponse = JSONResponse.generateResponse(HttpStatus.NOT_FOUND.value(), "Non è stato trovato alcun Dipartimento!");
	}
	
	@Test @Order(1) @SneakyThrows
	public final void testDeleteDepartment() {
		this.mockMcv.perform(MockMvcRequestBuilders.delete("/api/departments/Informatica")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(this.okResponse.toString()))
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andDo(print());	
	}
	
	@Test @Order(2) @SneakyThrows
	public final void testDeleteDepartmentNotFound() {
		this.mockMcv.perform(MockMvcRequestBuilders.delete("/api/departments/Informatica")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().json(this.notFoundResponse.toString()))
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andDo(print());	
	}
}
