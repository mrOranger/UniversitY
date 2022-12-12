package it.university.department.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.university.department.dao.ProfessorDAO;
import it.university.department.dao.impl.converter.Converter;
import it.university.department.dto.ProfessorDTO;
import it.university.department.entity.Professor;
import it.university.department.repository.ProfessorRepository;

public class ProfessorService implements ProfessorDAO, Converter<Professor, ProfessorDTO> {
	
	@Autowired private ProfessorRepository professorRepository;

	@Override
	public List<ProfessorDTO> findAllByDepartment(String department) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProfessorDTO findDirectorByDepartment(String department) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProfessorDTO convertToDto(Professor f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProfessorDTO> convertToDto(List<Professor> f) {
		// TODO Auto-generated method stub
		return null;
	}

}
