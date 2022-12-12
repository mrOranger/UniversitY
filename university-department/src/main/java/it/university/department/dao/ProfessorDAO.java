package it.university.department.dao;

import java.util.List;

import it.university.department.dto.Professor;

public interface ProfessorDAO {
	
	public abstract List<Professor> findAllByDepartment(String department);
	public abstract Professor findDirectorByDepartment(String department);

}
