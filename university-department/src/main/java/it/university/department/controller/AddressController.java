package it.university.department.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.university.department.dao.impl.DepartmentService;
import it.university.department.dto.DepartmentDTO;
import it.university.department.entity.Address;
import it.university.department.entity.Faculty;
import it.university.department.entity.builder.DepartmentBuilder;
import it.university.department.exception.BindingException;
import it.university.department.exception.NotFoundException;
import it.university.department.message.Message;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

@RestController @Log
@RequestMapping(path = "api/departments/address/", produces = MediaType.APPLICATION_JSON_VALUE)
public final class AddressController {
	
	@Autowired private DepartmentService departmentService;
	@Autowired private DepartmentBuilder departmentBuilder;
	@Autowired private ResourceBundleMessageSource errorMessage;
	
	@GetMapping(path = "street/{street}") @SneakyThrows
	public final ResponseEntity<List<DepartmentDTO>> getDepartmentsByStreet(@PathVariable("street") String street) {
		log.info("[GET] - api/departments/address/street/ ".concat(street));
		
		final List<DepartmentDTO> departments = this.departmentService.findAllByAddressStreet(street);
		
		if(departments.isEmpty()) {
			throw new NotFoundException();
		}
		
		return new ResponseEntity<List<DepartmentDTO>>(departments, HttpStatus.OK);
	}
	
	@GetMapping(path = "city/{city}") @SneakyThrows
	public final ResponseEntity<List<DepartmentDTO>> getDepartmentsByCity(@PathVariable("city") String city) {
		log.info("[GET] - api/departments/address/city/ ".concat(city));
		
		final List<DepartmentDTO> departments = this.departmentService.findAllByAddressCity(city);
		
		if(departments.isEmpty()) {
			throw new NotFoundException();
		}
		
		return new ResponseEntity<List<DepartmentDTO>>(departments, HttpStatus.OK);
	}
	
	@GetMapping(path = "province/{province}") @SneakyThrows
	public final ResponseEntity<List<DepartmentDTO>> getDepartmentsByProvince(@PathVariable("province") String province) {
		log.info("[GET] - api/departments/address/province/ ".concat(province));
		
		final List<DepartmentDTO> departments = this.departmentService.findAllByAddressProvince(province);
		
		if(departments.isEmpty()) {
			throw new NotFoundException();
		}
		
		return new ResponseEntity<List<DepartmentDTO>>(departments, HttpStatus.OK);
	}
	
	@GetMapping(path = "region/{region}") @SneakyThrows
	public final ResponseEntity<List<DepartmentDTO>> getDepartmentsByRegion(@PathVariable("region") String region) {
		log.info("[GET] - api/departments/address/region/ ".concat(region));
		
		final List<DepartmentDTO> departments = this.departmentService.findAllByAddressRegion(region);
		
		if(departments.isEmpty()) {
			throw new NotFoundException();
		}
		
		return new ResponseEntity<List<DepartmentDTO>>(departments, HttpStatus.OK);
	}
	
	@GetMapping(path = "nation/{nation}") @SneakyThrows
	public final ResponseEntity<List<DepartmentDTO>> getDepartmentsByNation(@PathVariable("nation") String nation) {
		log.info("[GET] - api/departments/address/nation/ ".concat(nation));
		
		final List<DepartmentDTO> departments = this.departmentService.findAllByAddressNation(nation);
		
		if(departments.isEmpty()) {
			throw new NotFoundException();
		}
		
		return new ResponseEntity<List<DepartmentDTO>>(departments, HttpStatus.OK);
	}
	
	@GetMapping(path = "id/{id}") @SneakyThrows
	public final ResponseEntity<List<DepartmentDTO>> getDepartmentsByAddress(@PathVariable("id") int id) {
		log.info("[GET] - api/departments/address/id/ " + id);
		
		final List<DepartmentDTO> departments = this.departmentService.findAllByAddress(id);
		
		if(departments.isEmpty()) {
			throw new NotFoundException();
		}
		
		return new ResponseEntity<List<DepartmentDTO>>(departments, HttpStatus.OK);
	}
	
	@PutMapping(path = "department/{department}") @SneakyThrows
	public final ResponseEntity<Message> putDepartmentAddress(
			@PathVariable("department") String department, 
			@Valid @RequestBody Address address,
			BindingResult bindingResult) {
		
		log.info("[PUT] - api/departments/address/department/ ".concat(department));
		log.info(address.toString());
		
		if(bindingResult.hasErrors()) {
			throw new BindingException(this.errorMessage.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale()));
		}
		
		final DepartmentDTO foundDepartment = this.departmentService.findById(department);
		
		if(foundDepartment == null) {
			throw new NotFoundException();
		}
		
		this.departmentService.save(this.departmentBuilder
				.setName(department)
				.setAddress(address)
				.setFaculty(new Faculty(foundDepartment.getFaculty().getName()))
				.build());
		
		return new ResponseEntity<Message>(new Message(LocalDate.now(), "Dipartimento modificato con successo!", HttpStatus.CREATED.value()), HttpStatus.CREATED);		
	}
}
