package it.university.department.controller.department;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
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

import it.university.department.Setup;
import lombok.SneakyThrows;

@SpringBootTest @TestMethodOrder(OrderAnnotation.class) @Order(10)
public final class InsertTest implements Setup{
	
	private MockMvc mockMcv;
	@Autowired private WebApplicationContext context;
	
	private JSONObject department;
	private JSONObject okResponse;
	private JSONObject notAcceptableError;
	private JSONObject badRequestError;
	
	@Override @BeforeEach @SneakyThrows
	public void setup() {
		
		this.mockMcv = MockMvcBuilders.webAppContextSetup(context).build();
		
		this.okResponse = new JSONObject()
							.put("message", "Dipartimento inserito con successo!")
							.put("code", HttpStatus.OK.value());
		
		this.notAcceptableError = new JSONObject()
										.put("message", "Dipartimento già presente in memoria!")
										.put("code", HttpStatus.NOT_ACCEPTABLE.value());
		
		this.badRequestError = new JSONObject()
										.put("message", "Il nome del dipartimento non può essere nullo")
										.put("code", HttpStatus.BAD_REQUEST.value());
		
		this.department = new JSONObject()
				.put("name", "Informatica & TPS")
				.put("address", new JSONObject()
						.put("id", 6)
						.put("street", "Dam Square")
						.put("number", 6)
						.put("city", "Amsterdam")
						.put("province", null)
						.put("region", null)
						.put("nation", "Holland"))
				.put("faculty", new JSONObject()
									.put("name", "IMF"));
		
	}
	
	@Test @Order(1) @SneakyThrows
	public final void testInsertDepartment() {
		
		this.mockMcv.perform(MockMvcRequestBuilders.post("/api/departments")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.department.toString())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(this.okResponse.toString()))
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andDo(print());		
	}
	
	@Test @Order(2) @SneakyThrows
	public final void testInsertDepartmentNotAcceptable() {
		
		this.mockMcv.perform(MockMvcRequestBuilders.post("/api/departments")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.department.toString())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotAcceptable())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(this.notAcceptableError.toString()))
				.andDo(print());		
		
	}
	
	@Test @Order(3) @SneakyThrows
	public final void testInsertDepartmentBadRequest() {
		
		this.department.remove("name");
		
		this.mockMcv.perform(MockMvcRequestBuilders.post("/api/departments")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.department.toString())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(this.badRequestError.toString()))
				.andDo(print());		
		
	}
}
