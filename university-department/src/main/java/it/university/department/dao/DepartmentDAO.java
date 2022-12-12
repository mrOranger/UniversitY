package it.university.department.dao;

import java.util.List;

import it.university.department.dto.DepartmentDTO;

public interface DepartmentDAO {
	
	public abstract List<DepartmentDTO> findAll();
	public abstract DepartmentDTO findById(String id);
	public abstract List<DepartmentDTO> findAllByAddress(int id);
	public abstract List<DepartmentDTO> findAllByAddressStreet(String street);
	public abstract List<DepartmentDTO> findAllByAddressCity(String city);
	public abstract List<DepartmentDTO> findAllByAddressProvince(String province);
	public abstract List<DepartmentDTO> findAllByAddressRegion(String region);
	public abstract List<DepartmentDTO> findAllByAddressNation(String nation);
	public abstract List<DepartmentDTO> findAllByFaculty(String faculty);

}
