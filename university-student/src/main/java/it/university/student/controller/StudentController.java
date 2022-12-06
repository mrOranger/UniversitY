package it.university.student.controller;

import java.sql.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.university.student.dao.impl.StudentService;
import it.university.student.dto.StudentDTO;
import lombok.SneakyThrows;

@RestController @RequestMapping(path = "api/StudentDTOs/", produces = MediaType.APPLICATION_JSON_VALUE)
public final class StudentController {
	
	@Autowired private StudentService service;
	
	@RequestMapping(path = "find/") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudents() {
		return null;
	}
	
	@RequestMapping(path = "find/{id}") @SneakyThrows
	public final ResponseEntity<StudentDTO> getStudent(@PathParam("id") String id) {
		return null;
	}
	
	@RequestMapping(path = "find/date/{start}/{end}") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudentsByDateOfBirth(@PathParam("start") Date start, @PathParam("end") Date end) {
		return null;
	}
	
	@RequestMapping(path = "find/sex/{sex}") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudentsBySex(@PathParam("sex") char sex) {
		return null;
	}
	
	@RequestMapping(path = "find/bachelor/has") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudentsHavingBachelorDegreee() {
		return null;
	}
	
	@RequestMapping(path = "find/bachelor/has/not") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudentsNotHavingBachelorDegreee() {
		return null;
	}
	
	@RequestMapping(path = "find/diploma/grade/{grade}") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudentsByDiplomaGrade(@PathParam("grade") byte grade) {
		return null;
	}
	
	@RequestMapping(path = "find/diploma/bachelor/grade/{grade}") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudentsByBachelorGrade(@PathParam("grade") byte grade) {
		return null;
	}
	
	public final ResponseEntity<List<StudentDTO>> getStudentsByCity(String city) {
		return null;
	}
	
	public final ResponseEntity<List<StudentDTO>> getStudentsByProvince(String province) {
		return null;
	}
	
	public final ResponseEntity<List<StudentDTO>> getStudentsByRegion(String region) {
		return null;
	}
	
	public final ResponseEntity<List<StudentDTO>> getStudentsByNation(String nation) {
		return null;
	}
	
	public final ResponseEntity<List<StudentDTO>> getStudentsByAddress(int address) {
		return null;	
	}
}
