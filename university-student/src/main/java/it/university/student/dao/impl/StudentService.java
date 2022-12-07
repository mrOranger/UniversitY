package it.university.student.dao.impl;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.university.student.dao.StudentDao;
import it.university.student.dto.StudentDTO;
import it.university.student.entity.Student;
import it.university.student.repository.StudentRepository;

@Service @Transactional(readOnly = true)
public class StudentService implements StudentDao{
	
	@Autowired private StudentRepository repository;
	@Autowired private ModelMapper modelMapper;
	
	@Override
	public List<StudentDTO> findAll() {
		return this.convertToDto(StreamSupport.stream(this.repository.findAll().spliterator(), false)
				.collect(Collectors.toList()));
	}

	@Override
	public StudentDTO findById(String id) {
		return this.convertToDto(this.repository.findById(id).orElse(null));
	}

	@Override
	public List<StudentDTO> findAllByDate(Date start, Date end) {
		return this.convertToDto(this.repository
				.findByDateOfBirthBetween(start, end));
	}

	@Override
	public List<StudentDTO> findAllByBachelorDegree() {
		return this.convertToDto(this.repository.findByBachelorGradeNotNull());
	}
	
	@Override
	public List<StudentDTO> findAllByBachelorDegreeNot() {
		return this.convertToDto(this.repository.findByBachelorGradeIsNull());
	}

	@Override
	public List<StudentDTO> findAllByDiplomaGrade(int diplomaGrade) {
		return this.convertToDto(this.repository.findByDiplomaGrade(diplomaGrade));
	}

	@Override
	public List<StudentDTO> findAllByBachelorGrade(int bachelorGrade) {
		return this.convertToDto(this.repository.findByBachelorGrade(bachelorGrade));
	}

	@Override
	public List<StudentDTO> findAllByCity(String city) {
		return this.convertToDto(this.repository.findByAddressCity(city));
	}

	@Override
	public List<StudentDTO> findAllByProvince(String province) {
		return this.convertToDto(this.repository.findByAddressProvince(province));
	}

	@Override
	public List<StudentDTO> findAllByRegion(String region) {
		return this.convertToDto(this.repository.findByAddressRegion(region));
	}

	@Override
	public List<StudentDTO> findAllByNation(String nation) {
		return this.convertToDto(this.repository.findByAddressNation(nation));
	}

	@Override
	public List<StudentDTO> findAllByAddress(int address) {
		return this.convertToDto(this.repository.findByAddressId(address));
	}

	@Override @Transactional
	public StudentDTO save(Student student) {
		return this.convertToDto(this.repository.save(student));
	}

	@Override @Transactional
	public void deleteAll() {
		this.repository.deleteAll();
	}

	@Override @Transactional
	public void deleteStudent(String id) {
		this.repository.deleteById(id);
	}
	
	public StudentDTO convertToDto(Student student) {
		StudentDTO studentDto = null;
		
		if(student != null) {
			studentDto = this.modelMapper.map(student, StudentDTO.class);
		}
		
		return studentDto;
	}
	
	public List<StudentDTO> convertToDto(List<Student> students) {
		return students.stream()
				.map((source) -> this.modelMapper.map(source, StudentDTO.class))
				.collect(Collectors.toList());
	}
}
