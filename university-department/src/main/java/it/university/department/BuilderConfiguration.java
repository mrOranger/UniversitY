package it.university.department;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import it.university.department.entity.builder.AddressBuilder;
import it.university.department.entity.builder.DepartmentBuilder;
import it.university.department.entity.builder.FacultyBuilder;

@Configuration @Description("Classe di configurazione per i builders delle entità")
public class BuilderConfiguration {

	@Bean @Description("Builder per generare un indirizzo")
	public AddressBuilder getAddressBuilder() {
		return new AddressBuilder();
	}
	
	@Bean @Description("Builder per generare una facoltà")
	public FacultyBuilder getFacultyBuilder() {
		return new FacultyBuilder();
	}
	
	@Bean @Description("Builder per generare un dipartimento")
	public DepartmentBuilder getDeparmentBuilder() {
		return new DepartmentBuilder();
	}
}
