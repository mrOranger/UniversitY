package it.university.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.university.department.entity.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, String>{

}
