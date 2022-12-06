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
import it.university.student.dto.StudentDTO;
import it.university.student.repository.StudentRepository;

@Service @Transactional(readOnly = true)
public class StudentService implements StudentDao{
	
	@Autowired private StudentRepository repository;

	@Override
	public List<StudentDTO> findAll() {
		return StreamSupport.stream(this.repository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}

	@Override
	public StudentDTO findById(String id) {
		return this.repository.findById(id).orElse(null);
	}

	@Override
	public List<StudentDTO> findAllByDate(LocalDate start, LocalDate end) {
		return this.repository
				.findByDateOfBirthBetween(Date.valueOf(start), Date.valueOf(end));
	}

	@Override
	public List<StudentDTO> findAllBySex(char sex) {
		return this.repository.findBySex(sex);
	}

	@Override
	public List<StudentDTO> findAllByBachelorDegree() {
		return this.repository.findByBachelorGradeNotNull();
	}

	@Override
	public List<StudentDTO> findAllByDiplomaGrade(byte diplomaGrade) {
		return this.repository.findByDiplomaGrade(diplomaGrade);
	}

	@Override
	public List<StudentDTO> findAllByBachelorGrade(byte bachelorGrade) {
		return this.repository.findByBachelorGrade(bachelorGrade);
	}

	@Override
	public List<StudentDTO> findAllByCity(String city) {
		return this.repository.findByAddressCity(city);
	}

	@Override
	public List<StudentDTO> findAllByProvince(String province) {
		return this.repository.findByAddressProvince(province);
	}

	@Override
	public List<StudentDTO> findAllByRegion(String region) {
		return this.repository.findByAddressRegion(region);
	}

	@Override
	public List<StudentDTO> findAllByNation(String nation) {
		return this.repository.findByAddressNation(nation);
	}

	@Override
	public List<StudentDTO> findAllByAddress(int address) {
		return this.repository.findByAddressId(address);
	}

	@Override
	public List<StudentDTO> findAllByDepartment(String department) {
		return this.repository.findByDepartmentName(department);
	}

	@Override
	public List<StudentDTO> findAllByFaculty(String faculty) {
		return this.repository.findByDepartmentFacultyName(faculty);
	}

	@Override
	public List<StudentDTO> findAllByExam(int exam) {
		return this.repository.findByExamsId(exam);
	}

	@Override
	public List<StudentDTO> findAllByExamPresent(int exam) {
		return this.repository.findByExamsIdAndExamsVoteNotNull(exam);
	}

	@Override
	public List<StudentDTO> findAllByExamAbsente(int exam) {
		return this.repository.findByExamsIdAndExamsVoteIsNull(exam);
	}
	
	@Override
	public List<StudentDTO> findAllByExamVoteGreaterThan(int exam, byte vote) {
		return this.repository.findByExamsIdAndExamsVoteGreaterThan(exam, vote);
	}

	@Override
	public List<StudentDTO> findAllByExamVoteLowerThan(int exam, byte vote) {
		return this.repository.findByExamsIdAndExamsVoteLessThan(exam, vote);
	}

	@Override @Transactional
	public StudentDTO save(StudentDTO student) {
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
