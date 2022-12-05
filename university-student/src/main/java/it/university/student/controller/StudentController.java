package it.university.student.controller;

import java.sql.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.university.student.entity.Student;
import lombok.SneakyThrows;

@RestController @RequestMapping(path = "api/students/", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {
	
	@RequestMapping(path = "find/") @SneakyThrows
	public ResponseEntity<List<Student>> getStudents() {
		return null;
	}
	
	@RequestMapping(path = "find/{id}") @SneakyThrows
	public ResponseEntity<Student> getStudent(@PathParam("id") String id) {
		return null;
	}
	
	@RequestMapping(path = "find/date/{start}/{end}") @SneakyThrows
	public ResponseEntity<List<Student>> getStudentsByDateOfBirth(@PathParam("start") Date start, @PathParam("end") Date end) {
		return null;
	}
	
	@RequestMapping(path = "find/sex/{sex}") @SneakyThrows
	public ResponseEntity<List<Student>> getStudentsBySex(@PathParam("sex") char sex) {
		return null;
	}
	
	@RequestMapping(path = "find/bachelor/has") @SneakyThrows
	public ResponseEntity<List<Student>> getStudentsHavingBachelorDegreee() {
		return null;
	}
	
	@RequestMapping(path = "find/bachelor/has/not") @SneakyThrows
	public ResponseEntity<List<Student>> getStudentsNotHavingBachelorDegreee() {
		return null;
	}
	
	@RequestMapping(path = "find/diploma/grade/{grade}") @SneakyThrows
	public ResponseEntity<List<Student>> getStudentsByDiplomaGrade(@PathParam("grade") byte grade) {
		return null;
	}
	
	@RequestMapping(path = "find/diploma/bachelor/grade/{grade}") @SneakyThrows
	public ResponseEntity<List<Student>> getStudentsByBachelorGrade(@PathParam("grade") byte grade) {
		return null;
	}
	
	public ResponseEntity<List<Student>> getStudentsByCity(String city) {
		return null;
	}
	
	public ResponseEntity<List<Student>> getStudentsByProvince(String province) {
		return null;
	}
	
	public ResponseEntity<List<Student>> getStudentsByRegion(String region) {
		return null;
	}
	
	public ResponseEntity<List<Student>> getStudentsByNation(String nation) {
		return null;
	}
	
	public ResponseEntity<List<Student>> getStudentsByAddress(int address) {
		return null;	
	}
}
