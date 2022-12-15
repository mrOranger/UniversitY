package it.university.department.entity.builder;

import it.university.department.entity.Faculty;

public class FacultyBuilder implements Builder<Faculty>{
	
	private Faculty faculty;
	
	public FacultyBuilder () {
		this.faculty = new Faculty();
	}
	
	public FacultyBuilder setName(String name) {
		this.faculty.setName(name);
		return this;
	}

	@Override
	public void clean() {
		this.faculty = new Faculty();
	}
	
	@Override
	public Faculty build() {
		return this.faculty;
	}

}
