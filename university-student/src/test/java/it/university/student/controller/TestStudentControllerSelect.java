package it.university.student.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import it.university.student.UniversityStudentApplication;

@ContextConfiguration(classes = UniversityStudentApplication.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TestStudentControllerSelect {
	
	private MockMvc mockMcv;
	@Autowired private WebApplicationContext context;
	
	private JSONObject student;
	private JSONObject address;
	
	private JSONObject errorNotFound;
	private JSONObject emptyCollection;
	
	@BeforeEach
	public void setup() throws JSONException, IOException {
		this.mockMcv = MockMvcBuilders.webAppContextSetup(context).build();
		
		this.student = new JSONObject();
		this.address = new JSONObject();
		
		this.errorNotFound = new JSONObject();
		this.emptyCollection = new JSONObject();
		
		this.errorNotFound
			.put("code", 404)
			.put("message", "Non è stato trovato alcuno studente!");
		
		this.emptyCollection
			.put("code", 404)
			.put("message", "Nessuno studente presente nel database!");
		
		address.put("id", 1)
			.put("street", "Via Nazionale")
			.put("number", 1)
			.put("city", "Milano")
			.put("province", "Milano")
			.put("region", "Lombardia")
			.put("nation", "Italia");

		student.put("id", "AB123")
			.put("name", "Mario")
			.put("surname", "Rossi")
			.put("dateOfBirth", "1991-01-01")
			.put("diplomaGrade", 89)
			.put("address", address);
		
	}
	
	@Test @Order(1)
	public void testGetStudentById() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/AB123")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(this.student.toString()))
				.andDo(print());		
	}
	
	@Test @Order(2)
	public void testGetStudentByIdError() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/UNKNOWN")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(this.errorNotFound.toString()))
				.andDo(print());	
	}
	
	@Test @Order(3)
	public void testGetStudentsByDateOfBirth() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/date/1990-01-01/2000-01-01")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(10)))
				.andDo(print());	
	}
	
	@Test @Order(4)
	public void testGetStudentsByDateOfBirthError() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/date/2020-01-01/2022-01-01")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(this.emptyCollection.toString()))
				.andDo(print());	
	}
	
	@Test @Order(5)
	public void testGetStudentsHavingBachelorDegree() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/bachelor/has")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(7)))
				.andDo(print());			
	}
	
	@Test @Order(6)
	public void testGetStudentsNotHavingBachelorDegree() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/bachelor/has/not")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(3)))
				.andDo(print());			
	}
	
	@Test @Order(7)
	public void testGetStudentsByDiplomaGradeHavingVote() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/diploma/grade/78")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(1)))
				.andDo(print());	
	}
	
	@Test @Order(8)
	public void testGetStudentsByDiplomaGradeHavingVoteError() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/diploma/grade/100")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(this.emptyCollection.toString()))
				.andDo(print());	
	}
	
	@Test @Order(9)
	public void testGetStudentHavingBachelorDegreeVote() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/bachelor/grade/104")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(1)))
				.andDo(print());	
	}
	
	@Test @Order(10)
	public void testGetStudentsHavingBachelorDegreeVoteError() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/bachelor/grade/99")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(this.emptyCollection.toString()))
				.andDo(print());
	}
	
	@Test @Order(11)
	public void testGetStudentsByCity() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/address/city/Milano")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(1)))
				.andDo(print());			
	}
	
	@Test @Order(12)
	public void testGetStudentsByCityError() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/address/city/Torino")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(1)))
				.andDo(print());				
	}

	@Test @Order(13)
	public void testGetStudentsByProvince() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/address/province/Milano")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(1)))
				.andDo(print());			
	}
	
	@Test @Order(14)
	public void testGetStudentsByProvinceError() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/address/province/Torino")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(1)))
				.andDo(print());				
	}
	
	@Test @Order(15)
	public void testGetStudentsByRegion() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/address/region/Lombardia")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(1)))
				.andDo(print());				
	}
	
	@Test @Order(16)
	public void testGetStudentsByRegionError() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/address/region/Abruzzo")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(this.emptyCollection.toString()))
				.andDo(print());		
	}
	
	@Test @Order(17)
	public void testGetStudentsByNation() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/address/nation/Italia")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(5)))
				.andDo(print());				
	}
	
	@Test @Order(18)
	public void testGetStudentsByNationError() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/address/nation/Canada")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(this.emptyCollection.toString()))
				.andDo(print());			
	}
	
	@Test @Order(19)
	public void testGetStudentsByAddressId() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/address/id/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(content().json(new JSONArray().put(this.student).toString()))
				.andDo(print());			
	}
	
	@Test @Order(20)
	public void testGetStudentsByAddressIdError() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/address/id/10")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(this.emptyCollection.toString()))
				.andDo(print());		
	}
}