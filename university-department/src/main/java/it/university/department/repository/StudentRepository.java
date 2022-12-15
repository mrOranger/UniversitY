package it.university.department.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.university.department.entity.Student;

public interface StudentRepository extends JpaRepository<Student, String>{
	
	@Query(value = "SELECT * FROM student WHERE department = :department", nativeQuery = true)
	public List<Student> findAllByDepartment(@Param("department") String department);
	
}
