package it.university.student.controller;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import it.university.student.UniversityStudentApplication;
import it.university.student.entity.Student;
import it.university.student.repository.StudentRepository;

@ContextConfiguration(classes = UniversityStudentApplication.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TestStudentControllerInsert {
	
	private MockMvc mockMcv;
	@Autowired private WebApplicationContext context;
	@Autowired private StudentRepository repository;
	
	private JSONObject student;
	private JSONObject address;
	
	@BeforeEach
	public void setup() throws JSONException, IOException {
		this.mockMcv = MockMvcBuilders.webAppContextSetup(context).build();
		
		this.student = new JSONObject();
		this.address = new JSONObject();
		
		address.put("street", "Via delle Rose")
			.put("number", 12)
			.put("city", "Pescara")
			.put("province", "Pescara")
			.put("region", "Abruzzo")
			.put("nation", "Italia");
		
		student.put("id", "MK871OZ")
			.put("name", "Alberto")
			.put("surname", "De Angelis")
			.put("dateOfBirth", LocalDate.parse("1996-11-01").toString())
			.put("diplomaGrade", (byte)91)
			.put("bachelorGrade", (byte)97)
			.put("address", address);
	}
	
	@Test @Order(1)
	public void testInsStudente() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.post("/api/students/post")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.student.toString())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.code").value(HttpStatus.CREATED.value()))
				.andExpect(jsonPath("$.date").value(LocalDate.now().toString()))
				.andExpect(jsonPath("$.message").value("Studente inserito con successo!"))
				.andDo(print());
		
		assertThat(this.repository.findById(this.student.get("id").toString()).get())
			.extracting(Student::getDateOfBirth)
			.isEqualTo(Date.valueOf(LocalDate.parse(this.student.getString("dateOfBirth"))));
		
	}
	
	@Test @Order(2)
	public void testInsStudenteErrorDuplicate() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.post("/api/students/post")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.student.toString())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotAcceptable())
				.andExpect(jsonPath("$.code").value(HttpStatus.NOT_ACCEPTABLE.value()))
				.andExpect(jsonPath("$.date").value(LocalDate.now().toString()))
				.andExpect(jsonPath("$.message").value("Studente già presente nel database!"))
				.andDo(print());
		
		assertThat(this.repository.findById(this.student.get("id").toString()).get())
			.extracting(Student::getDateOfBirth)
			.isEqualTo(Date.valueOf(LocalDate.parse(this.student.getString("dateOfBirth"))));
	}
	
	@Test @Order(3)
	public void testInsStudenteErrorBadRequest() throws Exception {
		
		this.student.remove("dateOfBirth");
		
		this.mockMcv.perform(MockMvcRequestBuilders.post("/api/students/post")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.student.toString())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.code").value(HttpStatus.BAD_REQUEST.value()))
				.andExpect(jsonPath("$.date").value(LocalDate.now().toString()))
				.andExpect(jsonPath("$.message").value("La data di nascita dello studente non può essere nulla"))
				.andDo(print());
		
		assertThat(this.repository.findById(this.student.get("id").toString()).get())
			.extracting(Student::getDateOfBirth)
			.isEqualTo(Date.valueOf(LocalDate.parse("1996-11-01")));
	}
}