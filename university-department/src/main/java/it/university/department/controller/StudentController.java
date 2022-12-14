package it.university.department.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.university.department.dao.impl.StudentService;
import it.university.department.dto.StudentDTO;
import it.university.department.exception.NotFoundException;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

// TODO: complete definition of controller for students
/*
 * [GET] -> api/departments/students/:department -> [Students]
 * [POST] -> api/department/students -> Message
 * [DELETE] -> api/departments/students/:studentId -> Message
 */
@RestController @Log
@RequestMapping(path = "api/departments/students")
public final class StudentController {
	
	@Autowired private StudentService studentService;
	
	@GetMapping(path = "/{department}") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudents(@PathVariable("department") String department) {
		log.info("[GET] -> api/departments/students/".concat(department));
		final List<StudentDTO> students = this.studentService.findAllByDepartment(department);
		if(students.isEmpty()) {
			throw new NotFoundException();
		}
		return new ResponseEntity<List<StudentDTO>>(students, HttpStatus.OK);
	}

}
