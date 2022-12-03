package it.university.student.entity.builder;

import java.sql.Date;

import it.university.student.entity.Course;
import it.university.student.entity.Department;
import it.university.student.entity.Faculty;
import it.university.student.entity.Professor;

public class ProfessorBuilder implements Builder<Professor>{
	
	private Professor professor;

	public ProfessorBuilder() {
		this.professor = new Professor();
	}

	public ProfessorBuilder(Professor professor) {
		this.professor = professor;
	}
	
	public ProfessorBuilder setId(String id) {
		this.professor.setId(id);
		return this;
	}
	
	public ProfessorBuilder setName(String name) {
		this.professor.setName(name);
		return this;
	}
	
	public ProfessorBuilder setSurname(String surname) {
		this.professor.setSurname(surname);
		return this;
	}
	
	public ProfessorBuilder setDateOfBirth(Date dateOfBirth) {
		this.professor.setDateOfBirth(dateOfBirth);
		return this;
	}
	
	public ProfessorBuilder setDeparment(Department department) {
		this.professor.setDepartment(department);
		return this;
	}
	
	public ProfessorBuilder setFaculty(Faculty faculty) {
		this.professor.setFaculty(faculty);
		return this;
	}
	
	public ProfessorBuilder addCourse(Course course) {
		this.professor.getCourses().add(course);
		return this;
	}

	@Override
	public Professor build() {
		return this.professor;
	}
}
