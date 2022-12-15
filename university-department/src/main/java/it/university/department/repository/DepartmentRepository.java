package it.university.department.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.university.department.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, String>{
	
	public abstract List<Department> findByAddressStreet(String street);
	public abstract List<Department> findByAddressCity(String city);
	public abstract List<Department> findByAddressProvince(String province);
	public abstract List<Department> findByAddressRegion(String region);
	public abstract List<Department> findByAddressNation(String nation);
	public abstract List<Department> findByAddressId(int id);
	public abstract List<Department> findByFacultyName(String name);

}
