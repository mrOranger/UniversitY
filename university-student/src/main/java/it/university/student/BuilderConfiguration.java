package it.university.student;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.university.student.entity.Address;
import it.university.student.entity.Student;
import it.university.student.entity.builder.AddressBuilder;
import it.university.student.entity.builder.Builder;
import it.university.student.entity.builder.StudentBuilder;

@Configuration
public class BuilderConfiguration {

	@Bean
	public Builder<Address> getAddressBuilder() {
		return new AddressBuilder();
	}
	
	@Bean
	public Builder<Student> getStudentBuilder() {
		return new StudentBuilder();
	}
}
