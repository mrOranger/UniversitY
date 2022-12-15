package it.university.department.controller.department;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import it.university.department.Setup;
import lombok.SneakyThrows;

@SpringBootTest @TestMethodOrder(OrderAnnotation.class) @Order(9)
public final class SelectTest implements Setup {
	
	private MockMvc mockMcv;
	@Autowired private WebApplicationContext context;
	
	private JSONObject notFoundError;
	private JSONObject department;

	@Override @SneakyThrows @BeforeEach
	public void setup() {
		this.mockMcv = MockMvcBuilders.webAppContextSetup(context).build();
		
		this.notFoundError = new JSONObject();
		this.notFoundError
			.put("code", 404)
			.put("message", "Non è stato trovato alcun Dipartimento!");
		
		this.department = new JSONObject();
		this.department.put("name", "Lettere")
						.put("address", new JSONObject()
								.put("id", 6)
								.put("street", "Dam Square")
								.put("number", 10)
								.put("city", "Amsterdam")
								.put("province", "")
								.put("region", "")
								.put("nation", "Holland"))
						.put("faculty", new JSONObject()
											.put("name", "LESU"));
	}
	
	@Test @Order(1) @SneakyThrows
	public void testSelectDepartments() {
		
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/departments")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(9)))
				.andDo(print());			
	}
	
	@Test @Order(2) @SneakyThrows
	public void testSelectDepartmentByName() {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/departments/Lettere")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(this.department.toString()))
				.andDo(print());
	}

	@Test @Order(3) @SneakyThrows
	public void testSelectDepartmentByNameFail() {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/departments/Veterinaria")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(this.notFoundError.toString()))
				.andDo(print());
	}
}
