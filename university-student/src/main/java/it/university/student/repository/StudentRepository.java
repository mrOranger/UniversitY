package it.university.student.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import it.university.student.dto.StudentDTO;

public interface StudentRepository extends PagingAndSortingRepository<StudentDTO, String>{
	
	public abstract List<StudentDTO> findByDateOfBirthBetween(Date start, Date end);
	public abstract List<StudentDTO> findBySex(char sex);
	public abstract List<StudentDTO> findByBachelorGradeNotNull();
	public abstract List<StudentDTO> findByDiplomaGrade(byte diplomaGrade);
	public abstract List<StudentDTO> findByBachelorGrade(byte bachelorGrade);
	
	public abstract List<StudentDTO> findByAddressCity(String city);
	public abstract List<StudentDTO> findByAddressProvince(String province);
	public abstract List<StudentDTO> findByAddressRegion(String region);
	public abstract List<StudentDTO> findByAddressNation(String nation);
	public abstract List<StudentDTO> findByAddressId(int id);
	
	public abstract List<StudentDTO> findByDepartmentName(String name);
	
	public abstract List<StudentDTO> findByDepartmentFacultyName(String faculty);
	
	public abstract List<StudentDTO> findByExamsId(int id);
	public abstract List<StudentDTO> findByExamsIdAndExamsVoteNotNull(int id);
	public abstract List<StudentDTO> findByExamsIdAndExamsVoteIsNull(int id);
	public abstract List<StudentDTO> findByExamsIdAndExamsVoteGreaterThan(int id, byte vote);
	public abstract List<StudentDTO> findByExamsIdAndExamsVoteLessThan(int id, byte vote);
}
