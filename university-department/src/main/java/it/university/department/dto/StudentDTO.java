package it.university.department.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public final class StudentDTO {
	
	private String id;
	private String name;
	private String surname;
	private Date dateOfBirth;
	private byte diplomaGrade;
	private byte bachelorGrade;

}
