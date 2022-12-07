package it.university.student.dao;

import java.sql.Date;
import java.util.List;

import it.university.student.dto.StudentDTO;
import it.university.student.entity.Student;

public interface StudentDao {

	public abstract List<StudentDTO> findAll();
	public abstract StudentDTO findById(String id);
	public abstract List<StudentDTO> findAllByDate(Date start, Date end);
	public abstract List<StudentDTO> findAllByBachelorDegree();
	public abstract List<StudentDTO> findAllByBachelorDegreeNot();
	public abstract List<StudentDTO> findAllByDiplomaGrade(int diplomaGrade);
	public abstract List<StudentDTO> findAllByBachelorGrade(int bachelorGrade);
	
	public abstract List<StudentDTO> findAllByCity(String city);
	public abstract List<StudentDTO> findAllByProvince(String province);
	public abstract List<StudentDTO> findAllByRegion(String region);
	public abstract List<StudentDTO> findAllByNation(String nation);
	public abstract List<StudentDTO> findAllByAddress(int address);
	
	public abstract StudentDTO save(Student student);
	
	public abstract void deleteAll();
	public abstract void deleteStudent(String id);
	
}
