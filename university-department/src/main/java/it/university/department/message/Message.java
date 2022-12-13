package it.university.department.message;

import java.time.LocalDate;

import lombok.Data;

@Data
public final class Message {
	
	private LocalDate date;
	private String message;
	private int code;

}
