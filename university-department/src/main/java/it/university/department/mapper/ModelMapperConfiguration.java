package it.university.department.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

	@Bean
	public ModelMapper getModelMapper() {
		final ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		modelMapper.addConverter(new DepartmentConverter());
		modelMapper.addConverter(new StudentConverter());
		modelMapper.addConverter(new ProfessorConverter());
		return modelMapper;
	}
	
}
