package it.university.student.entity.builder;

import it.university.student.entity.Department;
import it.university.student.entity.Faculty;

public class FacultyBuilder implements Builder<Faculty>{
	
	private Faculty faculty;

	public FacultyBuilder() {
		this.faculty = new Faculty();
	}

	public FacultyBuilder(Faculty faculty) {
		this.faculty = faculty;
	}
	
	public FacultyBuilder setName(String name) {
		this.faculty.setName(name);
		return this;
	}
	
	public FacultyBuilder addDepartment(Department department) {
		this.faculty.getDepartments().add(department);
		return this;
	}

	@Override
	public Faculty build() {
		return this.faculty;
	}
}
