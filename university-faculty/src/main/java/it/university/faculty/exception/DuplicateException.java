package it.university.faculty.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DuplicateException extends Exception {

	private static final long serialVersionUID = 6361354735769518087L;
	
	private String message;
	
	public DuplicateException() {
		this.message = "Facoltà già presente in memoria!";
	}

	public DuplicateException(String message) {
		this.message = message;
	}
}