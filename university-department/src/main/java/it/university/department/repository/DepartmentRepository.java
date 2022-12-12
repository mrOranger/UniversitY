package it.university.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.university.department.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, String>{

}
