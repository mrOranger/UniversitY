package it.university.faculty.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public final class BindingException extends Exception {
	
	private static final long serialVersionUID = 3805880931518857069L;

	private String message;

	public BindingException(String message) {
		this.message = message;
	}
}
