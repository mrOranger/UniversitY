package it.university.faculty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.university.faculty.dto.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, String> {

}
