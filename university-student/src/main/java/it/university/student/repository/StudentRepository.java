package it.university.student.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import it.university.student.entity.Student;

public interface StudentRepository extends PagingAndSortingRepository<Student, String>{
	
	public abstract List<Student> findByDateOfBirthBetween(Date start, Date end);
	public abstract List<Student> findBySex(char sex);
	public abstract List<Student> findByBachelorGradeNotNull();
	public abstract List<Student> findByDiplomaGrade(byte diplomaGrade);
	public abstract List<Student> findByBachelorGrade(byte bachelorGrade);
	
	public abstract List<Student> findByAddressCity(String city);
	public abstract List<Student> findByAddressProvince(String province);
	public abstract List<Student> findByAddressRegion(String region);
	public abstract List<Student> findByAddressNation(String nation);
	public abstract List<Student> findByAddressId(int id);
	
	public abstract List<Student> findByDepartmentName(String name);
	
	public abstract List<Student> findByDepartmentFacultyName(String faculty);
	
	public abstract List<Student> findByExamsId(int id);
	public abstract List<Student> findByExamsIdAndExamsVoteNotNull(int id);
	public abstract List<Student> findByExamsIdAndExamsVoteIsNull(int id);
	public abstract List<Student> findByExamsIdAndExamsVoteGreaterThan(int id, byte vote);
	public abstract List<Student> findByExamsIdAndExamsVoteLessThan(int id, byte vote);
}
