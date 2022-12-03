package it.university.student;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.university.student.entity.Address;
import it.university.student.entity.Course;
import it.university.student.entity.Department;
import it.university.student.entity.Exam;
import it.university.student.entity.Faculty;
import it.university.student.entity.Professor;
import it.university.student.entity.Student;
import it.university.student.entity.builder.AddressBuilder;
import it.university.student.entity.builder.Builder;
import it.university.student.entity.builder.CourseBuilder;
import it.university.student.entity.builder.DepartmentBuilder;
import it.university.student.entity.builder.ExamBuilder;
import it.university.student.entity.builder.FacultyBuilder;
import it.university.student.entity.builder.ProfessorBuilder;
import it.university.student.entity.builder.StudentBuilder;

@Configuration
public class BuilderConfiguration {

	@Bean
	public Builder<Address> getAddressBuilder() {
		return new AddressBuilder();
	}
	
	@Bean
	public Builder<Course> getCourseBuilder() {
		return new CourseBuilder();
	}
	
	@Bean
	public Builder<Department> getDepartmentBuilder() {
		return new DepartmentBuilder();
	}
	
	@Bean
	public Builder<Exam> getExamBuilder() {
		return new ExamBuilder();
	}
	
	@Bean
	public Builder<Faculty> getFacultyBuilder() {
		return new FacultyBuilder();
	}
	
	@Bean
	public Builder<Professor> getProfessorBuilder() {
		return new ProfessorBuilder();
	}
	
	@Bean
	public Builder<Student> getStudentBuilder() {
		return new StudentBuilder();
	}
}
