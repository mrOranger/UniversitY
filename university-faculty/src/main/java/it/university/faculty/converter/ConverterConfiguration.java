package it.university.faculty.converter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Scope;

import it.university.faculty.dao.service.converter.FacultyConverter;

@Configuration
public final class ConverterConfiguration {

	@Bean @Scope("singleton") @Description("Bean per il converter di DTO di tipo Faculty")
	public FacultyConverter facultyConverter() {
		return new FacultyConverter();
	}
	
}
