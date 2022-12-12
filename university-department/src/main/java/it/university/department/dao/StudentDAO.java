package it.university.department.dao;

import java.util.List;

import it.university.department.dto.StudentDTO;

public interface StudentDAO {
	
	public abstract List<StudentDTO> findAllByDepartment(String department);

}
