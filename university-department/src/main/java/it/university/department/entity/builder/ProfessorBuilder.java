package it.university.department.entity.builder;

import java.sql.Date;

import it.university.department.entity.Address;
import it.university.department.entity.Department;
import it.university.department.entity.Professor;

public final class ProfessorBuilder implements Builder<Professor>{
	
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
	
	public ProfessorBuilder setWorksIn(Department department) {
		this.professor.setWorksIn(department);
		department.getProfessors().add(this.professor);
		return this;
	}
	
	public ProfessorBuilder setDirector(Department department) {
		this.professor.setDirector(department);
		department.getDirectors().add(this.professor);
		return this;
	}
	
	public ProfessorBuilder setAddress(Address address) {
		this.professor.setAddress(address);
		address.getProfessors().add(this.professor);
		return this;
	}

	@Override
	public void clean() {
		this.professor = new Professor();
	}

	@Override
	public Professor build() {
		return this.professor;
	}

}
