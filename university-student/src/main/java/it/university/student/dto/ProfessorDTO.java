package it.university.student.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public final class ProfessorDTO {
	
	private String id;
	private String name;
	private String surname;
	private Date dateOfBirth;

}
