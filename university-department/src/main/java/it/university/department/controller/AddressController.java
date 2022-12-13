package it.university.department.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.university.department.entity.Address;
import it.university.department.entity.Department;
import it.university.department.message.Message;
import lombok.extern.java.Log;

@RestController @Log
@RequestMapping(path = "api/departments/address/", produces = MediaType.APPLICATION_JSON_VALUE)
public final class AddressController {
	
	public final ResponseEntity<List<Department>> getDepartmentsByStreet(String street) {
		log.info("[GET] - api/departments/address/street/ ".concat(street));
		return null;
	}
	
	public final ResponseEntity<List<Department>> getDepartmentsByCity(String city) {
		log.info("[GET] - api/departments/address/city/ ".concat(city));
		return null;
	}
	
	public final ResponseEntity<List<Department>> getDepartmentsByProvince(String province) {
		log.info("[GET] - api/departments/address/province/ ".concat(province));
		return null;
	}
	
	public final ResponseEntity<List<Department>> getDepartmentsByRegion(String region) {
		log.info("[GET] - api/departments/address/region/ ".concat(region));
		return null;
	}
	
	public final ResponseEntity<List<Department>> getDepartmentsByNation(String nation) {
		log.info("[GET] - api/departments/address/nation/ ".concat(nation));
		return null;
	}
	
	public final ResponseEntity<List<Department>> getDepartmentsByAddress(int id) {
		log.info("[GET] - api/departments/address/id/ " + id);
		return null;
	}
	
	public final ResponseEntity<Message> putDepartmentAddress(String department, Address address) {
		log.info("[PUT] - api/departments/address/nation/ ".concat(department));
		log.info(address.toString());
		return null;
	}
}
