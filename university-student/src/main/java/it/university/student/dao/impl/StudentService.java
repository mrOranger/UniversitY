package it.university.student.dao.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;

import it.university.student.dao.StudentDao;
import it.university.student.entity.Student;
import it.university.student.repository.StudentRepository;

public class StudentService implements StudentDao{
	
	@Autowired private StudentRepository repository;

	@Override
	public List<Student> findAll() {
		return StreamSupport.stream(this.repository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}

	@Override
	public Student findById(String id) {
		return this.repository.findById(id).orElse(null);
	}

	@Override
	public List<Student> findAllByDate(LocalDate start, LocalDate end) {
		return this.repository
				.findByDateOfBirthBetweenStartAndEnd(Date.valueOf(start), Date.valueOf(end));
	}

	@Override
	public List<Student> findAllBySex(char sex) {
		return this.repository.findBySex(sex);
	}

	@Override
	public List<Student> findAllByBachelorDegree() {
		return this.repository.findByBachelorGradeNotNull();
	}

	@Override
	public List<Student> findAllByDiplomaGrade(byte diplomaGrade) {
		return this.repository.findByDiplomaGrade(diplomaGrade);
	}

	@Override
	public List<Student> findAllByBachelorGrade(byte bachelorGrade) {
		return this.repository.findByBachelorGrade(bachelorGrade);
	}

}
