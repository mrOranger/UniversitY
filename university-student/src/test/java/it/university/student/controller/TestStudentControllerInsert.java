package it.university.student.controller;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import java.io.IOException;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import it.university.student.UniversityStudentApplication;

@ContextConfiguration(classes = UniversityStudentApplication.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TestStudentControllerInsert {
	
	private MockMvc mockMcv;
	@Autowired private WebApplicationContext context;
	
	@BeforeEach
	public void setup() throws JSONException, IOException {
		this.mockMcv = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
}
