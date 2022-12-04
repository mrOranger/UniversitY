package it.university.student.dao;

import java.time.LocalDate;
import java.util.List;

import it.university.student.entity.Student;

public interface StudentDao {

	public abstract List<Student> findAll();
	public abstract Student findById(String id);
	public abstract List<Student> findAllByDate(LocalDate start, LocalDate end);
	public abstract List<Student> findAllBySex(char sex);
	public abstract List<Student> findAllByBachelorDegree();
	public abstract List<Student> findAllByDiplomaGrade(byte diplomaGrade);
	public abstract List<Student> findAllByBachelorGrade(byte bachelorGrade);
	
	public abstract List<Student> findAllByCity(String city);
	public abstract List<Student> findAllByProvince(String province);
	public abstract List<Student> findAllByRegion(String region);
	public abstract List<Student> findAllByNation(String nation);
	public abstract List<Student> findAllByAddress(int address);
	
	public abstract List<Student> findAllByDepartment(String department);
	
	public abstract List<Student> findAllByFaculty(String faculty);
	
	public abstract List<Student> findAllByExam(int exam);
	public abstract List<Student> findAllByExamPresent(int exam);
	public abstract List<Student> findAllByExamAbsente(int exam);
	public abstract List<Student> findAllByExamVoteGreaterThan(int exam, byte vote);
	public abstract List<Student> findAllByExamVoteLowerThan(int exam, byte vote);
	
	public abstract Student save(Student student);
	
	public abstract void deleteAll();
	public abstract void deleteStudent(String id);
	
}
