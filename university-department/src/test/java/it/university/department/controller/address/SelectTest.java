package it.university.department.controller.address;

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

@SpringBootTest @TestMethodOrder(OrderAnnotation.class) @Order(7)
public final class SelectTest implements Setup{
	
	private MockMvc mockMcv;
	@Autowired private WebApplicationContext context;
	
	private JSONObject notFoundError;
	
	@Override @BeforeEach @SneakyThrows
	public void setup() {
		this.mockMcv = MockMvcBuilders.webAppContextSetup(context).build();
		
		this.notFoundError = new JSONObject();
		this.notFoundError
			.put("code", 404)
			.put("message", "Non è stato trovato alcun Dipartimento!");
	}
	
	
	@Test @Order(1) @SneakyThrows
	public void testSelectDepartmentByStreet() {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/departments/address/street/Via Nazionale")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(5)))
				.andDo(print());	
	}
	
	@Test @Order(2) @SneakyThrows
	public void testSelectDepartmentByCity() {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/departments/address/city/Amsterdam")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(2)))
				.andDo(print());	
	}
	
	@Test @Order(3) @SneakyThrows
	public void testSelectDepartmentByProvince() {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/departments/address/province/Bari")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(1)))
				.andDo(print());	
	}
	
	@Test @Order(4) @SneakyThrows
	public void testSelectDepartmentByRegion() {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/departments/address/region/Sicilia")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(2)))
				.andDo(print());	
	}
	
	@Test @Order(5) @SneakyThrows
	public void testSelectDepartmentByNation() {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/departments/address/nation/Italia")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(5)))
				.andDo(print());	
	}
	
	@Test @Order(6) @SneakyThrows
	public void testSelectDepartmentById() {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/departments/address/id/4")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(2)))
				.andDo(print());	
	}
	
	@Test @Order(7) @SneakyThrows
	public void testSelectDepartmentByStreetFail() {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/departments/address/street/1st Avenue")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(this.notFoundError.toString()))
				.andDo(print());	
	}
	
	@Test @Order(8) @SneakyThrows
	public void testSelectDepartmentByCityFail() {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/departments/address/city/Boston")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(this.notFoundError.toString()))
				.andDo(print());	
	}
	
	@Test @Order(9) @SneakyThrows
	public void testSelectDepartmentByProvinceFail() {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/departments/address/province/Venezia")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(this.notFoundError.toString()))
				.andDo(print());	
	}
	
	@Test @Order(10) @SneakyThrows
	public void testSelectDepartmentByRegionFail() {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/departments/address/region/Abruzzo")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(this.notFoundError.toString()))
				.andDo(print());	
	}
	
	@Test @Order(11) @SneakyThrows
	public void testSelectDepartmentByNationFail() {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/departments/address/city/Canada")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(this.notFoundError.toString()))
				.andDo(print());	
	}
	
	@Test @Order(12) @SneakyThrows
	public void testSelectDepartmentByIdFail() {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/departments/address/id/6")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(this.notFoundError.toString()))
				.andDo(print());	
	}
}
