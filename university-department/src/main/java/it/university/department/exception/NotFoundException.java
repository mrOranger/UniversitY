package it.university.department.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public final class NotFoundException extends Exception {

	private static final long serialVersionUID = -6716151828187696900L;
	
	private String message;
	
	public NotFoundException() {
		this.message = "Non è stato trovato alcun Dipartimento!";
	}
	
	public NotFoundException(String message) {
		this.message = message;
	}
}
