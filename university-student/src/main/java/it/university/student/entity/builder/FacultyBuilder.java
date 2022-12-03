package it.university.student.entity.builder;

import it.university.student.entity.Faculty;

public class FacultyBuilder implements Builder<Faculty>{
	
	private Faculty faculty;

	public FacultyBuilder() {
		this.faculty = new Faculty();
	}
	
	public FacultyBuilder setName(String name) {
		this.faculty.setName(name);
		return this;
	}

	@Override
	public Faculty build() {
		return this.faculty;
	}

	@Override
	public void clean() {
		this.faculty = new Faculty();
	}
}
