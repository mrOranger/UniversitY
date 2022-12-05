package it.university.student.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public final class ExamDTO {
	
	private int id;
	private Date date;
	private byte vote;
	private CourseDTO course;

}
