package it.university.student.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NotFoundException extends Exception{

	private static final long serialVersionUID = -3136951204807066373L;
	
	private String message;
	
	public NotFoundException() {
		this.message = "Non è stato trovato alcuno studente!";
	}

	public NotFoundException(String message) {
		this.message = message;
	}
}
