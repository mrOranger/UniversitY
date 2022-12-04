package it.university.student.entity.builder;

import java.sql.Date;

import it.university.student.entity.Professor;

public class ProfessorBuilder implements Builder<Professor>{
	
	private Professor professor;

	public ProfessorBuilder() {
		this.professor = new Professor();
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

	@Override
	public Professor build() {
		return this.professor;
	}

	@Override
	public void clean() {
		this.professor = new Professor();
	}
}
