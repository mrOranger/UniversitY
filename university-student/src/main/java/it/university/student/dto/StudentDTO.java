package it.university.student.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public final class StudentDTO {
	
	private String id;
	private String name;
	private String surname;
	private char sex;
	private Date dateOfBirth;
	private byte diplomaGrade;
	private byte bachelorGrade;
	
	private AddressDTO address;
}
