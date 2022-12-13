package it.university.department.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import it.university.department.dao.impl.DepartmentService;
import it.university.department.dto.DepartmentDTO;
import it.university.department.entity.Address;
import it.university.department.message.Message;
import lombok.extern.java.Log;

@RestController @Log
@RequestMapping(path = "api/departments/address/", produces = MediaType.APPLICATION_JSON_VALUE)
public final class AddressController {
	
	@Autowired private DepartmentService departmentService;
	
	@RequestMapping(path = "street/:street")
	public final ResponseEntity<List<DepartmentDTO>> getDepartmentsByStreet(@PathVariable("street") String street) {
		log.info("[GET] - api/departments/address/street/ ".concat(street));
		
		final List<DepartmentDTO> departments = this.departmentService.findAllByAddressStreet(street);
		
		return null;
	}
	
	@RequestMapping(path = "city/:city")
	public final ResponseEntity<List<DepartmentDTO>> getDepartmentsByCity(@PathVariable("city") String city) {
		log.info("[GET] - api/departments/address/city/ ".concat(city));
		
		final List<DepartmentDTO> departments = this.departmentService.findAllByAddressCity(city);
		return null;
	}
	
	@RequestMapping(path = "province/:province")
	public final ResponseEntity<List<DepartmentDTO>> getDepartmentsByProvince(@PathVariable("province") String province) {
		log.info("[GET] - api/departments/address/province/ ".concat(province));
		
		final List<DepartmentDTO> departments = this.departmentService.findAllByAddressProvince(province);
		
		return null;
	}
	
	@RequestMapping(path = "region/:region")
	public final ResponseEntity<List<DepartmentDTO>> getDepartmentsByRegion(@PathVariable("region") String region) {
		log.info("[GET] - api/departments/address/region/ ".concat(region));
		
		final List<DepartmentDTO> departments = this.departmentService.findAllByAddressRegion(region);
		
		return null;
	}
	
	@RequestMapping(path = "nation/:nation")
	public final ResponseEntity<List<DepartmentDTO>> getDepartmentsByNation(@PathVariable("nation") String nation) {
		log.info("[GET] - api/departments/address/nation/ ".concat(nation));
		
		final List<DepartmentDTO> departments = this.departmentService.findAllByAddressNation(nation);
		
		return null;
	}
	
	@RequestMapping(path = "id/:id")
	public final ResponseEntity<List<DepartmentDTO>> getDepartmentsByAddress(@PathVariable("id") int id) {
		log.info("[GET] - api/departments/address/id/ " + id);
		
		final List<DepartmentDTO> departments = this.departmentService.findAllByAddress(id);
		
		return null;
	}
	
	@RequestMapping(path = "department/:department")
	public final ResponseEntity<Message> putDepartmentAddress(
			@PathVariable("department") String department, 
			@Valid @RequestBody Address address,
			BindingResult bindingResult) {
		
		log.info("[PUT] - api/departments/address/department/ ".concat(department));
		log.info(address.toString());
		return null;
	}
}
