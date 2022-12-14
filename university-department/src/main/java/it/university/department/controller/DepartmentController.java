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

// TODO: complete definition of controller for departments
/*
 * GET -> api/departments/ -> [Department]
 * GET -> api/departments/:name -> Department
 * POST -> api/departments -> Department
 * PUT -> api/departments/:name -> Department
 * PATCH -> api/departments/:oldName/name/:newName -> Department
 * DELETE -> api/departments/:name -> Department
 *  {
 *  	name: string,
 *  	address: {
 *  		id: integer,
 *  		street: string,
 *  		number: integer,
 *  		city: string,
 *  		province: string,
 *  		region: string,
 *  		nation: string,
 *  	},
 *  	faculty: {
 *  		name: string
 *  	}
 *  }
 */
@RestController
@Log @RequestMapping(path = "api/departments", produces = MediaType.APPLICATION_JSON_VALUE)
public final class DepartmentController {
	
	@Autowired private DepartmentService departmentService;
	
	@GetMapping @SneakyThrows
	public final ResponseEntity<List<DepartmentDTO>> getDepartments() {
		log.info("[GET] - api/departments ");
		final List<DepartmentDTO> departments = this.departmentService.findAll();
		if(departments.isEmpty()) {
			throw new NotFoundException();
		}
		return new ResponseEntity<List<DepartmentDTO>>(departments, HttpStatus.OK);
	}
	
	@GetMapping(path = "/{name}") @SneakyThrows
	public final ResponseEntity<DepartmentDTO> getDepartment(@PathVariable("name") String name) {
		log.info("[GET] - api/departments/".concat(name));
		final DepartmentDTO department = this.departmentService.findById(name);
		if(department == null) {
			throw new NotFoundException();
		}
		return new ResponseEntity<DepartmentDTO>(department, HttpStatus.OK);
	}

}
