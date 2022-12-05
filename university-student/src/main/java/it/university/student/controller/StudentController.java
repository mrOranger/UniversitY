package it.university.student.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import it.university.student.entity.Student;

@RestController 
public class StudentController {
	
	public ResponseEntity<List<Student>> getStudents() {
		return null;
	}
	
	public ResponseEntity<Student> getStudent(String id) {
		return null;
	}
	
	public ResponseEntity<List<Student>> getStudentsByDateOfBirth(Date start, Date end) {
		return null;
	}
	
	public ResponseEntity<List<Student>> getStudentsBySex(char sex) {
		return null;
	}
	
	public ResponseEntity<List<Student>> getStudentsHavingBachelorDegreee() {
		return null;
	}
	
	public ResponseEntity<List<Student>> getStudentsByDiplomaGrade(byte grade) {
		return null;
	}
	
	public ResponseEntity<List<Student>> getStudentsByBachelorGrade(byte grade) {
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
