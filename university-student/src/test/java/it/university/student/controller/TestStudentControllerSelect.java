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
	private JSONObject department;
	private JSONObject faculty;
	private JSONArray exams;
	private JSONObject exam;
	private JSONObject course;
	private JSONObject professor;
	
	private JSONObject errorNotFound;
	
	@BeforeEach
	public void setup() throws JSONException, IOException {
		this.mockMcv = MockMvcBuilders.webAppContextSetup(context).build();
		
		this.student = new JSONObject();
		this.address = new JSONObject();
		this.department = new JSONObject();
		this.faculty = new JSONObject();
		this.faculty = new JSONObject();
		this.exams = new JSONArray();
		this.exam = new JSONObject();
		this.course = new JSONObject();
		this.professor = new JSONObject();
		
		this.errorNotFound = new JSONObject();
		
		this.errorNotFound
			.put("code", 404)
			.put("message", "Non è stato trovato alcuno studente!");
		
		address.put("id", 6)
			.put("street", "Via Nazionale")
			.put("number", 123)
			.put("city", "Milano")
			.put("province", "Milano")
			.put("region", "Lombardia")
			.put("nation", "Italia");
	
		faculty.put("name", "Facoltà 1");
		
		department.put("name", "Dipartimento 1")
			.put("faculty", faculty);
		
		professor.put("id", "XK123JH")
			.put("name", "Maria")
			.put("surname", "Rossi")
			.put("date_of_birth", "2022-01-01");
		
		course.put("name", "Analisi 1")
			.put("start_date", "2022-12-05")
			.put("professor", professor);
		
		exam.put("date", "2022-01-01")
			.put("vote", (byte)21)
			.put("course", course);
		
		exams.put(exam);
		
		student.put("id", "AB123CD")
			.put("name", "Mario")
			.put("surname", "Rossi")
			.put("sex", 'M')
			.put("date_of_birth", "1990-01-01")
			.put("diploma_grade", (byte)79)
			.put("bachelor_grade", (byte)105)
			.put("address", address)
			.put("department", department)
			.put("exams", exams);
		
	}
	
	@Test @Order(1)
	public void testGetStudentById() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/AB123CD")
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
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/date/1980-01-01/2000-01-01")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(content().json(this.student.toString()))
				.andDo(print());	
	}
	
	@Test @Order(4)
	public void testGetStudentsByDateOfBirthError() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/date/2020-01/2022-01-01")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(this.errorNotFound.toString()))
				.andDo(print());	
	}
	
	@Test @Order(5)
	public void testGetStudentsBySex() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/sex/M")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(content().json(this.student.toString()))
				.andDo(print());	
	}
	
	@Test @Order(6)
	public void testGetStudentsBySexError() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/sex/F")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(this.errorNotFound.toString()))
				.andDo(print());	
	}
	
	@Test @Order(7)
	public void testGetStudentsByBachelorDegree() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/bachelor/has")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(content().json(this.student.toString()))
				.andDo(print());			
	}
	
	@Test @Order(8)
	public void testGetStudentsByBachelorDegreeError() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/bachelor/has/not")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(this.errorNotFound.toString()))
				.andDo(print());			
	}
	
	@Test @Order(9)
	public void testGetStudentsByDiplomaGrade() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/diploma/grade/60")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(content().json(this.student.toString()))
				.andDo(print());	
	}
	
	@Test @Order(10)
	public void testGetStudentsByDiplomaGradeError() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/diploma/grade/100")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(this.errorNotFound.toString()))
				.andDo(print());	
	}
	
	@Test @Order(11)
	public void testGetStudentsByBachelorGrade() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/bachelor/grade/70")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(content().json(this.student.toString()))
				.andDo(print());	
	}
	
	@Test @Order(12)
	public void testGetStudentsByBachelorGradeError() throws Exception {
		this.mockMcv.perform(MockMvcRequestBuilders.get("/api/students/find/bachelor/grade/110")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(this.errorNotFound.toString()))
				.andDo(print());
	}
}
