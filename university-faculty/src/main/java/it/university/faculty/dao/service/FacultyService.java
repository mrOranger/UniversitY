package it.university.faculty.dao.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.university.faculty.dao.FacultyDAO;
import it.university.faculty.dto.Faculty;
import it.university.faculty.entity.FacultyEntity;
import it.university.faculty.repository.FacultyRepository;

@Service @Transactional(readOnly = true)
public class FacultyService implements FacultyDAO {
	
	@Autowired private FacultyRepository facultyRepository;
	@Autowired private ModelMapper modelMapper;

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
		if(faculty.isPresent()) {
			return this.modelMapper.map(faculty.get(), Faculty.class);
		}
		return null;
	}
	
	private List<Faculty> convertToDto(List<FacultyEntity> faculties){
		return faculties.stream().map((currFaculty) ->
					this.modelMapper.map(currFaculty, Faculty.class))
				.collect(Collectors.toList());
	}
}
