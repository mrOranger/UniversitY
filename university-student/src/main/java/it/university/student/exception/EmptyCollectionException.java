package it.university.student.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmptyCollectionException extends Exception {

	private static final long serialVersionUID = 4850102606003505421L;
	
	private String message;
	
	public EmptyCollectionException() {
		this.message = "Nessuno studente presente nel database!";
	}

	public EmptyCollectionException(String message) {
		this.message = message;
	}

}
