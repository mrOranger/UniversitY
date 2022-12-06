package it.university.student.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.university.student.dao.impl.StudentService;
import it.university.student.dto.StudentDTO;
import it.university.student.exception.EmptyCollectionException;
import it.university.student.exception.NotFoundException;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

@RestController @RequestMapping(path = "api/students/", produces = MediaType.APPLICATION_JSON_VALUE)
@Log
public final class StudentController {
	
	@Autowired private StudentService service;
	
	@GetMapping(path = "find/") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudents() {
		final List<StudentDTO> students = this.service.findAll();
		if(students.isEmpty()) {
			throw new EmptyCollectionException();
		}
		return new ResponseEntity<List<StudentDTO>>(students, HttpStatus.OK);
	}
	
	@GetMapping(path = "find/{id}") @SneakyThrows
	public final ResponseEntity<StudentDTO> getStudent(@PathVariable("id") String id) {
		log.info("Richiesto studente con id".concat(id));
		final StudentDTO student = this.service.findById(id);
		if(student == null) {
			throw new NotFoundException();
		}
		return new ResponseEntity<StudentDTO>(student, HttpStatus.OK);
	}
	
	@GetMapping(path = "find/date/{start}/{end}") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudentsByDateOfBirth(@PathVariable("start") Date start, @PathVariable("end") Date end) {
		final List<StudentDTO> students = this.service.findAllByDate(start, end);
		if(students.isEmpty()) {
			throw new EmptyCollectionException();
		}
		return new ResponseEntity<List<StudentDTO>>(students, HttpStatus.OK);
	}
	
	@GetMapping(path = "find/sex/{sex}") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudentsBySex(@PathVariable("sex") char sex) {
		final List<StudentDTO> students = this.service.findAllBySex(sex);
		if(students.isEmpty()) {
			throw new EmptyCollectionException();
		}
		return new ResponseEntity<List<StudentDTO>>(students, HttpStatus.OK);
	}
	
	@GetMapping(path = "find/bachelor/has") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudentsHavingBachelorDegreee() {
		final List<StudentDTO> students = this.service.findAllByBachelorDegree();
		if(students.isEmpty()) {
			throw new EmptyCollectionException();
		}
		return new ResponseEntity<List<StudentDTO>>(students, HttpStatus.OK);
	}
	
	@GetMapping(path = "find/bachelor/has/not") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudentsNotHavingBachelorDegreee() {
		final List<StudentDTO> students = this.service.findAllByBachelorDegreeNot();
		if(students.isEmpty()) {
			throw new EmptyCollectionException();
		}
		return new ResponseEntity<List<StudentDTO>>(students, HttpStatus.OK);
	}
	
	@GetMapping(path = "find/diploma/grade/{grade}") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudentsByDiplomaGrade(@PathVariable("grade") byte grade) {
		final List<StudentDTO> students = this.service.findAllByDiplomaGrade(grade);
		if(students.isEmpty()) {
			throw new EmptyCollectionException();
		}
		return new ResponseEntity<List<StudentDTO>>(students, HttpStatus.OK);
	}
	
	@GetMapping(path = "find/bachelor/grade/{grade}") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudentsByBachelorGrade(@PathVariable("grade") byte grade) {
		final List<StudentDTO> students = this.service.findAllByBachelorGrade(grade);
		if(students.isEmpty()) {
			throw new EmptyCollectionException();
		}
		return new ResponseEntity<List<StudentDTO>>(students, HttpStatus.OK);
	}
	
	@GetMapping(path = "find/address/city/{city}") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudentsByCity(@PathVariable("city") String city) {
		final List<StudentDTO> students = this.service.findAllByCity(city);
		if(students.isEmpty()) {
			throw new EmptyCollectionException();
		}
		return new ResponseEntity<List<StudentDTO>>(students, HttpStatus.OK);
	}
	
	@GetMapping(path = "find/address/province/{province}") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudentsByProvince(@PathVariable("province") String province) {
		final List<StudentDTO> students = this.service.findAllByProvince(province);
		if(students.isEmpty()) {
			throw new EmptyCollectionException();
		}
		return new ResponseEntity<List<StudentDTO>>(students, HttpStatus.OK);
	}
	
	@GetMapping(path = "find/address/region/{region}") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudentsByRegion(@PathVariable("region") String region) {
		final List<StudentDTO> students = this.service.findAllByRegion(region);
		if(students.isEmpty()) {
			throw new EmptyCollectionException();
		}
		return new ResponseEntity<List<StudentDTO>>(students, HttpStatus.OK);
	}
	
	@GetMapping(path = "find/address/nation/{nation}") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudentsByNation(@PathVariable("nation") String nation) {
		final List<StudentDTO> students = this.service.findAllByNation(nation);
		if(students.isEmpty()) {
			throw new EmptyCollectionException();
		}
		return new ResponseEntity<List<StudentDTO>>(students, HttpStatus.OK);
	}
	
	@GetMapping(path = "find/address/id/{address}") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudentsByAddress(@PathVariable("address") int address) {
		final List<StudentDTO> students = this.service.findAllByAddress(address);
		if(students.isEmpty()) {
			throw new EmptyCollectionException();
		}
		return new ResponseEntity<List<StudentDTO>>(students, HttpStatus.OK);
	}
}
