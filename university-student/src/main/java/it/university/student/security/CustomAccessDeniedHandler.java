package it.university.student.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		final String errorMessage = "Privilegi Insufficienti. Impossibile Proseguire";
		HttpStatus httpStatus = HttpStatus.FORBIDDEN;
		response.setStatus(httpStatus.value());
		response.getOutputStream().println(errorMessage);
	}

	
	
}
