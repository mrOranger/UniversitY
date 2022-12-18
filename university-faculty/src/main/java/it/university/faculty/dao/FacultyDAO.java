package it.university.faculty.dao;

import java.util.List;
import java.util.Optional;

import it.university.faculty.dto.Faculty;
import it.university.faculty.entity.FacultyEntity;
public interface FacultyDAO {
	
	public abstract List<Faculty> findAll();
	public abstract Optional<Faculty> findById(String name);
	public abstract List<Faculty> findAllByCity(String city);
	public abstract List<Faculty> findAllByProvince(String province);
	public abstract List<Faculty> findAllByRegion(String region);
	public abstract List<Faculty> findAllByNation(String nation);
	
	public abstract Optional<Faculty> save(FacultyEntity faculty);
	
	public abstract void delete(String faculty);
	
}
