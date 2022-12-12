package it.university.department.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public final class ProfessorDTO {

	private String id;
	private String name;
	private String surname;
	private Date dateOfBirth;
	private DepartmentDTO worksIn;
	private DepartmentDTO director;
	
}