package it.university.department.dao;

import java.util.List;

import it.university.department.dto.Student;

public interface StudentDAO {
	
	public abstract List<Student> findAllByDepartment(String department);

}
