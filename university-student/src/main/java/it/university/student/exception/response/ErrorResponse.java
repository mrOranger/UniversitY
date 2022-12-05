package it.university.student.exception.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ErrorResponse {
	
	private LocalDate date;
	private String message;
	private int code;

}
