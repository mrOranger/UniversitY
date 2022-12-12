package it.university.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.university.department.entity.Student;

public interface StudentRepository extends JpaRepository<Student, String>{

}
