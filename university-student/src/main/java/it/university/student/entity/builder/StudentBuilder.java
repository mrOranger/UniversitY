package it.university.student.entity.builder;

import java.sql.Date;

import it.university.student.entity.Address;
import it.university.student.entity.Student;

public class StudentBuilder implements Builder<Student>{
	
	private Student student;
	
	public StudentBuilder() {
		this.student = new Student();
	}

	public StudentBuilder setId(String id) {
		this.student.setId(id);
		return this;
	}
	
	public StudentBuilder setName(String name) {
		this.student.setName(name);
		return this;
	}
	
	public StudentBuilder setSurname(String surname) {
		this.student.setSurname(surname);
		return this;
	}
	
	public StudentBuilder setDateOfBirth(Date dateOfBirth) {
		this.student.setDateOfBirth(dateOfBirth);
		return this;
	}
	
	public StudentBuilder setDiplomaGrade(Integer grade) {
		this.student.setDiplomaGrade(grade);
		return this;
	}
	
	public StudentBuilder setBachelorGrade(Integer grade) {
		this.student.setBachelorGrade(grade);
		return this;
	}
	
	public StudentBuilder setAddress(Address address) {
		this.student.setAddress(address);
		address.getStudents().add(student);
		return this;
	}

	@Override
	public Student build() {
		return this.student;
	}

	@Override
	public void clean() {
		this.student = new Student();
	}
}
