package it.university.department.exception.handler;

import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import it.university.department.exception.BindingException;
import it.university.department.exception.DuplicateException;
import it.university.department.exception.NotFoundException;
import it.university.department.message.Message;

@ControllerAdvice
@RestController
public final class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<Message> notFoundExceptionHandler(Exception ex) {
		final Message response = new Message();
		response.setDate(LocalDate.now());
		response.setMessage(ex.getMessage());
		response.setCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Message>(response, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BindingException.class)
	public final ResponseEntity<Message> bindingExceptionHandler(Exception ex) {
		final Message response = new Message();
		response.setDate(LocalDate.now());
		response.setMessage(ex.getMessage());
		response.setCode(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<Message>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DuplicateException.class)
	public final ResponseEntity<Message> duplicateExceptionHandler(Exception ex) {
		final Message response = new Message();
		response.setDate(LocalDate.now());
		response.setMessage(ex.getMessage());
		response.setCode(HttpStatus.NOT_ACCEPTABLE.value());
		return new ResponseEntity<Message>(response, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
	}
}