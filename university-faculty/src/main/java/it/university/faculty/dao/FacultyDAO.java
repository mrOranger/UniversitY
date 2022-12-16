package it.university.faculty.dao;

import java.util.List;

import it.university.faculty.dto.Faculty;

public interface FacultyDAO {
	
	public abstract List<Faculty> findAll();
	public abstract Faculty findById(String name);
	public abstract List<Faculty> findAllByCity(String city);
	public abstract List<Faculty> findAllByProvince(String province);
	public abstract List<Faculty> findAllByRegion(String region);
	public abstract List<Faculty> findAllByNation(String nation);
	
}
