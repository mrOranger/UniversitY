package it.university.faculty.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import it.university.faculty.dao.FacultyDAO;
import it.university.faculty.dto.Faculty;
import it.university.faculty.entity.FacultyEntity;
import it.university.faculty.repository.FacultyRepository;

public class FacultyService implements FacultyDAO {
	
	@Autowired private FacultyRepository facultyRepository;

	@Override
	public List<Faculty> findAll() {
		return this.convertToDto(this.facultyRepository.findAll());
	}

	@Override
	public Faculty findById(String name) {
		return this.convertToDto(this.facultyRepository.findById(name));
	}

	@Override
	public List<Faculty> findAllByCity(String city) {
		return this.convertToDto(this.facultyRepository.findByAddressCity(city));
	}

	@Override
	public List<Faculty> findAllByProvince(String province) {
		return this.convertToDto(this.facultyRepository.findByAddressProvince(province));
	}

	@Override
	public List<Faculty> findAllByRegion(String region) {
		return this.convertToDto(this.facultyRepository.findByAddressRegion(region));
	}

	@Override
	public List<Faculty> findAllByNation(String nation) {
		return this.convertToDto(this.facultyRepository.findByAddressNation(nation));
	}
	
	private Faculty convertToDto(Optional<FacultyEntity> faculty) {
		return null;
	}
	
	private List<Faculty> convertToDto(List<FacultyEntity> faculties){
		return null;
	}
}
