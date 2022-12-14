package it.university.student.exception.handler;

import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import it.university.student.exception.BindingException;
import it.university.student.exception.DuplicateException;
import it.university.student.exception.EmptyCollectionException;
import it.university.student.exception.NotFoundException;
import it.university.student.exception.response.ErrorResponse;

@ControllerAdvice @RestController
public final class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<ErrorResponse> notFoundExceptionHandler(Exception ex) {
		final ErrorResponse response = new ErrorResponse();
		response.setDate(LocalDate.now());
		response.setMessage(ex.getMessage());
		response.setCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorResponse>(response, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmptyCollectionException.class)
	public final ResponseEntity<ErrorResponse> emptyCollectionExceptionHandler(Exception ex) {
		final ErrorResponse response = new ErrorResponse();
		response.setDate(LocalDate.now());
		response.setMessage(ex.getMessage());
		response.setCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorResponse>(response, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BindingException.class)
	public final ResponseEntity<ErrorResponse> bindingExceptionHandler(Exception ex) {
		final ErrorResponse response = new ErrorResponse();
		response.setDate(LocalDate.now());
		response.setMessage(ex.getMessage());
		response.setCode(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ErrorResponse>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DuplicateException.class)
	public final ResponseEntity<ErrorResponse> duplicateExceptionHandler(Exception ex) {
		final ErrorResponse response = new ErrorResponse();
		response.setDate(LocalDate.now());
		response.setMessage(ex.getMessage());
		response.setCode(HttpStatus.NOT_ACCEPTABLE.value());
		return new ResponseEntity<ErrorResponse>(response, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
	}
}