package it.university.faculty.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.university.faculty.dao.FacultyDAO;
import it.university.faculty.dao.service.converter.FacultyConverter;
import it.university.faculty.dto.Faculty;
import it.university.faculty.repository.FacultyRepository;

@Service @Transactional(readOnly = true)
public class FacultyService implements FacultyDAO {
	
	@Autowired private FacultyRepository facultyRepository;
	@Autowired private FacultyConverter converter;

	@Override
	public List<Faculty> findAll() {
		return converter.convertToDto(facultyRepository.findAll());
	}

	@Override
	public Optional<Faculty> findById(String name) {
		return converter.convertToDto(facultyRepository.findById(name));
	}

	@Override
	public List<Faculty> findAllByCity(String city) {
		return converter.convertToDto(facultyRepository.findByAddressCity(city));
	}

	@Override
	public List<Faculty> findAllByProvince(String province) {
		return converter.convertToDto(facultyRepository.findByAddressProvince(province));
	}

	@Override
	public List<Faculty> findAllByRegion(String region) {
		return converter.convertToDto(facultyRepository.findByAddressRegion(region));
	}

	@Override
	public List<Faculty> findAllByNation(String nation) {
		return converter.convertToDto(facultyRepository.findByAddressNation(nation));
	}
}
