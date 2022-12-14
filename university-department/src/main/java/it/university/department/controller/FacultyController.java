package it.university.department.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.university.department.dao.impl.DepartmentService;
import it.university.department.dto.DepartmentDTO;
import it.university.department.exception.NotFoundException;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

@RestController @Log
@RequestMapping(path = "/api/departments/faculty", produces = MediaType.APPLICATION_JSON_VALUE)
public final class FacultyController {
	
	@Autowired private DepartmentService departmentService;
	
	@GetMapping(path = "/{faculty}") @SneakyThrows
	public final ResponseEntity<List<DepartmentDTO>> getDepartmentsByFaculty(@PathVariable("faculty") String faculty) {
		log.info("[GET] - /api/departments/faculty/".concat(faculty));
		final List<DepartmentDTO> departments = this.departmentService.findAllByFaculty(faculty);
		if(departments.isEmpty()) {
			throw new NotFoundException();
		}
		return new ResponseEntity<List<DepartmentDTO>>(departments, HttpStatus.OK);
	}

}
