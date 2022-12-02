package it.university.student.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import it.university.student.entity.Address;
import it.university.student.entity.Department;
import it.university.student.entity.Exam;
import it.university.student.entity.Faculty;
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
	public abstract List<Student> findByAddress(Address address);
	
	public abstract List<Student> findByDepartment(Department department);
	
	public abstract List<Student> findByDepartmentFaculty(Faculty faculty);
	
	public abstract List<Student> findByExams(Exam exam);
	public abstract List<Student> findByExamsVoteNotNull(Exam exam);
	public abstract List<Student> findByExamsVoteIsNull(Exam exam);
	public abstract List<Student> findByExamsVoteGreaterThan(Exam exam, byte vote);
	public abstract List<Student> findByExamsVoteLowerThan(Exam exam, byte vote);
}
