package it.university.department.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.university.department.entity.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, String>{

	@Query(value = "SELECT * FROM professor WHERE department = :department", nativeQuery = true)
	public abstract List<Professor> findByWorksInName(@Param("department") String department);
	
	@Query(value = "SELECT * FROM professor WHERE director = :department", nativeQuery = true)
	public abstract List<Professor> findByDirectorName(@Param("department") String department);
	
}