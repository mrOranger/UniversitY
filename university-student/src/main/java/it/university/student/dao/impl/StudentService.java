package it.university.student.dao.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.university.student.dao.StudentDao;
import it.university.student.entity.Student;
import it.university.student.repository.StudentRepository;

@Service @Transactional(readOnly = true)
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
				.findByDateOfBirthBetween(Date.valueOf(start), Date.valueOf(end));
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

	@Override
	public List<Student> findAllByCity(String city) {
		return this.repository.findByAddressCity(city);
	}

	@Override
	public List<Student> findAllByProvince(String province) {
		return this.repository.findByAddressProvince(province);
	}

	@Override
	public List<Student> findAllByRegion(String region) {
		return this.repository.findByAddressRegion(region);
	}

	@Override
	public List<Student> findAllByNation(String nation) {
		return this.repository.findByAddressNation(nation);
	}

	@Override
	public List<Student> findAllByAddress(int address) {
		return this.repository.findByAddressId(address);
	}

	@Override
	public List<Student> findAllByDepartment(String department) {
		return this.repository.findByDepartmentName(department);
	}

	@Override
	public List<Student> findAllByFaculty(String faculty) {
		return this.repository.findByDepartmentFacultyName(faculty);
	}

	@Override
	public List<Student> findAllByExam(int exam) {
		return this.repository.findByExamsId(exam);
	}

	@Override
	public List<Student> findAllByExamPresent(int exam) {
		return this.repository.findByExamsIdAndExamsVoteNotNull(exam);
	}

	@Override
	public List<Student> findAllByExamAbsente(int exam) {
		return this.repository.findByExamsIdAndExamsVoteIsNull(exam);
	}
	
	@Override
	public List<Student> findAllByExamVoteGreaterThan(int exam, byte vote) {
		return this.repository.findByExamsIdAndExamsVoteGreaterThan(exam, vote);
	}

	@Override
	public List<Student> findAllByExamVoteLowerThan(int exam, byte vote) {
		return this.repository.findByExamsIdAndExamsVoteLessThan(exam, vote);
	}

	@Override @Transactional
	public Student save(Student student) {
		return this.repository.save(student);
	}

	@Override @Transactional
	public void deleteAll() {
		this.repository.deleteAll();
	}

	@Override @Transactional
	public void deleteStudent(String id) {
		this.repository.deleteById(id);
	}
}
