package it.university.department.dao;

import java.util.List;

import it.university.department.dto.ProfessorDTO;

public interface ProfessorDAO {
	
	public abstract List<ProfessorDTO> findAllByDepartment(String department);
	public abstract List<ProfessorDTO> findDirectorsByDepartment(String department);

}
