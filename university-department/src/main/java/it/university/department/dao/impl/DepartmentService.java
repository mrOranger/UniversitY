package it.university.department.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.university.department.dao.DepartmentDAO;
import it.university.department.dao.impl.converter.Converter;
import it.university.department.dto.DepartmentDTO;
import it.university.department.entity.Department;
import it.university.department.repository.DepartmentRepository;

@Service @Transactional(readOnly = true)
public class DepartmentService implements DepartmentDAO, Converter<Department, DepartmentDTO> {
	
	@Autowired private DepartmentRepository repository;
	@Autowired private ModelMapper modelMapper;
	
	@Override
	public List<DepartmentDTO> findAll() {
		return this.convertToDto(this.repository.findAll());
	}

	@Override
	public DepartmentDTO findById(String id) {
		return this.convertToDto(this.repository.findById(id).orElse(null));
	}

	@Override
	public List<DepartmentDTO> findAllByAddress(int id) {
		return this.convertToDto(this.repository.findByAddressId(id));
	}

	@Override
	public List<DepartmentDTO> findAllByAddressStreet(String street) {
		return this.convertToDto(this.repository.findByAddressStreet(street));
	}

	@Override
	public List<DepartmentDTO> findAllByAddressCity(String city) {
		return this.convertToDto(this.repository.findByAddressCity(city));
	}

	@Override
	public List<DepartmentDTO> findAllByAddressProvince(String province) {
		return this.convertToDto(this.repository.findByAddressProvince(province));
	}

	@Override
	public List<DepartmentDTO> findAllByAddressRegion(String region) {
		return this.convertToDto(this.repository.findByAddressRegion(region));
	}

	@Override
	public List<DepartmentDTO> findAllByAddressNation(String nation) {
		return this.convertToDto(this.repository.findByAddressNation(nation));
	}

	@Override
	public List<DepartmentDTO> findAllByFaculty(String faculty) {
		return this.convertToDto(this.repository.findByFacultyName(faculty));
	}
	
	@Override @Transactional
	public DepartmentDTO save(Department department) {
		return this.convertToDto(this.repository.save(department));
	}
	
	@Override @Transactional
	public void delete(String department) {
		this.repository.deleteById(department);
	}

	@Override
	public DepartmentDTO convertToDto(Department f) {
		DepartmentDTO departmentDto = null;
		if(f != null) {
			departmentDto = this.modelMapper.map(f, DepartmentDTO.class);
		}
		return departmentDto;
	}

	@Override
	public List<DepartmentDTO> convertToDto(List<Department> f) {
		return f.stream()
				.map((source) -> this.modelMapper.map(source, DepartmentDTO.class))
				.collect(Collectors.toList());
	}
}
