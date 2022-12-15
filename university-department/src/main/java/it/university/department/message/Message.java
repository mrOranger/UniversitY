package it.university.department.message;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public final class Message {
	
	private LocalDate date;
	private String message;
	private int code;

}
