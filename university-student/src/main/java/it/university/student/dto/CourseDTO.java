package it.university.student.dto;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import it.university.student.entity.Professor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public final class CourseDTO {
	
	private int id;
	private String name;
	private Date date;
	private Set<Professor> professors = new HashSet<>();

}
