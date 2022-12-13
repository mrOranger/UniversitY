package it.university.department.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.university.department.entity.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, String>{

	@Query(value = "SELECT * FROM professor WHERE department = :1", nativeQuery = true)
	public abstract List<Professor> findByWorksInName(String department);
	
	@Query(value = "SELECT * FROM professor WHERE director = :1", nativeQuery = true)
	public abstract List<Professor> findByDirectorName(String department);
	
}