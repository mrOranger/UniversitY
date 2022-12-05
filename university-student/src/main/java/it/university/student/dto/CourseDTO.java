package it.university.student.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public final class CourseDTO {
	
	private String name;
	private Date startDate;
	private ProfessorDTO professor;

}
