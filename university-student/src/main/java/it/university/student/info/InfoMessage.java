package it.university.student.info;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class InfoMessage implements Serializable {
	
	private static final long serialVersionUID = -6449461953789098679L;
	
	private int code;
	private LocalDate date;
	private String message;

}