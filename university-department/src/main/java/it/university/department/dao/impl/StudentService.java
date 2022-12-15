package it.university.department.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.university.department.dao.StudentDAO;
import it.university.department.dao.impl.converter.Converter;
import it.university.department.dto.StudentDTO;
import it.university.department.entity.Student;
import it.university.department.repository.StudentRepository;

@Service @Transactional(readOnly = true)
@CacheConfig(cacheNames = { "studenti" })
public class StudentService implements StudentDAO, Converter<Student, StudentDTO> {
	
	@Autowired private StudentRepository studentRepository;
	@Autowired private ModelMapper modelMapper;

	@Override @Cacheable(value = "studenti.department", key = "#department", sync = true)
	public List<StudentDTO> findAllByDepartment(String department) {
		return this.convertToDto(this.studentRepository.findAllByDepartment(department));
	}

	@Override
	public StudentDTO convertToDto(Student f) {
		StudentDTO studentDto = null;
		if(f != null) {
			studentDto = this.modelMapper.map(f, StudentDTO.class);
		}	
		return studentDto;
	}

	@Override
	public List<StudentDTO> convertToDto(List<Student> f) {
		return f.stream()
				.map((source) -> this.modelMapper.map(source, StudentDTO.class))
				.collect(Collectors.toList());
	}
}
