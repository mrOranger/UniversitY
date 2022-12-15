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
import it.university.department.dao.impl.ProfessorService;
import it.university.department.dto.DepartmentDTO;
import it.university.department.dto.ProfessorDTO;
import it.university.department.exception.NotFoundException;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

@RestController @Log
@RequestMapping(path = "api/departments/professors", produces = MediaType.APPLICATION_JSON_VALUE)
public final class ProfessorController {
	
	@Autowired private ProfessorService professorService;
	@Autowired private DepartmentService departmentService;
	 
	@GetMapping(path = "/{name}") @SneakyThrows
	public final ResponseEntity<List<ProfessorDTO>> getProfessors(@PathVariable("name") String name) {
		log.info("[GET] - api/departments/professors/".concat(name));
		
		final DepartmentDTO department = this.departmentService.findById(name);
		if(department == null) {
			throw new NotFoundException("Non è stato trovato alcun Dipartimento!");
		}
		
		final List<ProfessorDTO> professors = this.professorService.findAllByDepartment(name);
		if(professors.isEmpty()) {
			throw new NotFoundException("Nessun professore è iscritto a questo dipartimento!");
		}
		
		return new ResponseEntity<List<ProfessorDTO>>(professors, HttpStatus.OK);
	}
	
	@GetMapping(path = "/directors/{name}") @SneakyThrows
	public final ResponseEntity<List<ProfessorDTO>> getDirectors(@PathVariable("name") String name) {
		log.info("[GET] - api/departments/professors/directors/".concat(name));
		
		final DepartmentDTO department = this.departmentService.findById(name);
		if(department == null) {
			throw new NotFoundException("Non è stato trovato alcun Dipartimento!");
		}
		
		final List<ProfessorDTO> professors = this.professorService.findDirectorsByDepartment(name);
		if(professors.isEmpty()) {
			throw new NotFoundException("Il dipartimento non ha un direttore!");
		}
		
		return new ResponseEntity<List<ProfessorDTO>>(professors, HttpStatus.OK);
	}

}
