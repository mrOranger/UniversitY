package it.university.student.controller;

import java.sql.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.university.student.entity.Student;

@RestController @RequestMapping(path = "api/students/")
public class StudentController {
	
	@RequestMapping(path = "find/")
	public ResponseEntity<List<Student>> getStudents() {
		return null;
	}
	
	@RequestMapping(path = "find/{id}")
	public ResponseEntity<Student> getStudent(@PathParam("id") String id) {
		return null;
	}
	
	@RequestMapping(path = "find/date/{start}/{end}")
	public ResponseEntity<List<Student>> getStudentsByDateOfBirth(@PathParam("start") Date start, @PathParam("end") Date end) {
		return null;
	}
	
	@RequestMapping(path = "find/sex/{sex}")
	public ResponseEntity<List<Student>> getStudentsBySex(@PathParam("sex") char sex) {
		return null;
	}
	
	@RequestMapping(path = "find/bachelor/has")
	public ResponseEntity<List<Student>> getStudentsHavingBachelorDegreee() {
		return null;
	}
	
	@RequestMapping(path = "find/bachelor/has/not")
	public ResponseEntity<List<Student>> getStudentsNotHavingBachelorDegreee() {
		return null;
	}
	
	@RequestMapping(path = "find/diploma/grade/{grade}")
	public ResponseEntity<List<Student>> getStudentsByDiplomaGrade(@PathParam("grade") byte grade) {
		return null;
	}
	
	@RequestMapping(path = "find/diploma/bachelor/grade/{grade}")
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
