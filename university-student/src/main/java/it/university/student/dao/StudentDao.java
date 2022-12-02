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
	
}
