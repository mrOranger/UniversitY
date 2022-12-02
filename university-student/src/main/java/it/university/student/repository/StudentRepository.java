package it.university.student.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import it.university.student.entity.Student;

public interface StudentRepository extends PagingAndSortingRepository<Student, String>{
	
	public abstract List<Student> findByDateOfBirthBetweenStartAndEnd(Date start, Date end);
	public abstract List<Student> findBySex(char sex);
	public abstract List<Student> findByBachelorGradeNotNull();
	public abstract List<Student> findByDiplomaGrade(byte diplomaGrade);
	public abstract List<Student> findByBachelorGrade(byte bachelorGrade);
	
}
