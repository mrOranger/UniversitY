package it.university.department.controller.address;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

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

@SpringBootTest @TestMethodOrder(OrderAnnotation.class) @Order(8)
public final class UpdateTest implements Setup{
	
	private MockMvc mockMcv;
	@Autowired private WebApplicationContext context;
	
	private JSONObject address;

	@Override @BeforeEach @SneakyThrows
	public void setup() {
		this.mockMcv = MockMvcBuilders.webAppContextSetup(context).build();
		
		this.address = new JSONObject();
		
		address.put("street", "Via delle Rose")
			.put("number", 12)
			.put("city", "Torino")
			.put("province", "Torino")
			.put("region", "Piemonte")
			.put("nation", "Italia");
	}
	
	@Test @Order(1) @SneakyThrows
	public void testUpdateDepartmentAddress() {
		this.mockMcv.perform(MockMvcRequestBuilders.put("/api/departments/address/Ingegneria Informatica")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.address.toString())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.code").value(HttpStatus.CREATED.value()))
				.andExpect(jsonPath("$.date").value(LocalDate.now().toString()))
				.andExpect(jsonPath("$.message").value("Dipartimento modificato con successo!"))
				.andDo(print());				
	}
	
	@Test @Order(2) @SneakyThrows
	public void testUpdateDepartmentAddressNotFound() {
		this.mockMcv.perform(MockMvcRequestBuilders.put("/api/departments/address/Ingegneria Matematica")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.address.toString())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.code").value(HttpStatus.NOT_FOUND.value()))
				.andExpect(jsonPath("$.date").value(LocalDate.now().toString()))
				.andExpect(jsonPath("$.message").value("Non è stato trovato alcun Dipartimento!"))
				.andDo(print());			
	}
	
	@Test @Order(3) @SneakyThrows
	public void testUpdateDepartmentAddressBadRequest() {
		
		this.address.remove("nation");
		
		this.mockMcv.perform(MockMvcRequestBuilders.put("/api/departments/address/Ingegneria Informatica")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.address.toString())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.code").value(HttpStatus.BAD_REQUEST.value()))
				.andExpect(jsonPath("$.date").value(LocalDate.now().toString()))
				.andExpect(jsonPath("$.message").value("La nazione di un Dipartimento non può essere nulla!"))
				.andDo(print());	
	}

}
