package it.university.student.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BindingException extends Exception {

	private static final long serialVersionUID = 3805880931518857069L;

	private String message;
	
	public BindingException() {
		this.message = "Non è stato trovato alcuno studente!";
	}

	public BindingException(String message) {
		this.message = message;
	}
}