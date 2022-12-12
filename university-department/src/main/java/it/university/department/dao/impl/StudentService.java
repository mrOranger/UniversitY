package it.university.department.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.university.department.dao.StudentDAO;
import it.university.department.dao.impl.converter.Converter;
import it.university.department.dto.StudentDTO;
import it.university.department.entity.Student;
import it.university.department.repository.StudentRepository;

public class StudentService implements StudentDAO, Converter<Student, StudentDTO> {
	
	@Autowired private StudentRepository studentRepository;

	@Override
	public List<StudentDTO> findAllByDepartment(String department) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentDTO convertToDto(Student f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentDTO> convertToDto(List<Student> f) {
		// TODO Auto-generated method stub
		return null;
	}

}
