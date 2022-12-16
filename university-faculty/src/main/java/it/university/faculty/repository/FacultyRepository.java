package it.university.faculty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.university.faculty.entity.FacultyEntity;

public interface FacultyRepository extends JpaRepository<FacultyEntity, String> {

	public abstract List<FacultyEntity> findByAddressCity(String city);
	public abstract List<FacultyEntity> findByAddressProvince(String province);
	public abstract List<FacultyEntity> findByAddressRegion(String region);
	public abstract List<FacultyEntity> findByAddressNation(String nation);
	
}
