package it.university.department.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.university.department.entity.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, String>{

	public abstract List<Professor> findByWorksInName(String department);
	public abstract Professor findByDirectorId(String id);
	
}
