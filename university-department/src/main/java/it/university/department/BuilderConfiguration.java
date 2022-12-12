package it.university.department;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.university.department.entity.Address;
import it.university.department.entity.Faculty;
import it.university.department.entity.Student;
import it.university.department.entity.builder.AddressBuilder;
import it.university.department.entity.builder.Builder;
import it.university.department.entity.builder.FacultyBuilder;
import it.university.department.entity.builder.StudentBuilder;

@Configuration
public class BuilderConfiguration {

	@Bean
	public Builder<Address> getAddressBuilder() {
		return new AddressBuilder();
	}
	
	@Bean
	public Builder<Faculty> getFacultyBuilder() {
		return new FacultyBuilder();
	}
	
	@Bean
	public Builder<Student> getStudentBuilder() {
		return new StudentBuilder();
	}
	
}
