package it.university.department.dao;

import java.util.List;

import it.university.department.dto.Department;

public interface DepartmentDAO {
	
	public abstract List<Department> findAll();
	public abstract Department findById(String id);
	public abstract List<Department> findAllByAddress(int id);
	public abstract List<Department> findAllByAddressStreet(String street);
	public abstract List<Department> findAllByAddressCity(String city);
	public abstract List<Department> findAllByAddressProvince(String province);
	public abstract List<Department> findAllByAddressRegion(String region);
	public abstract List<Department> findAllByAddressNation(String nation);
	public abstract List<Department> findAllByFaculty(String faculty);

}
