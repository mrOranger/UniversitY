package it.university.department.mapper;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class ProfessorConverter implements Converter<String, String>{
	
	@Override
	public String convert(MappingContext<String, String> context) {
		return context.getSource() == null ? "" : context.getSource().trim();
	}
}
