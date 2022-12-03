package it.university.student.entity.builder;

import it.university.student.entity.Address;
import it.university.student.entity.Department;
import it.university.student.entity.Professor;
import it.university.student.entity.Student;

public class DepartmentBuilder implements Builder<Department>{
	
	private Department department;

	public DepartmentBuilder() {
		this.department = new Department();
	}

	public DepartmentBuilder setName(String name) {
		this.department.setName(name);
		return this;
	}
	
	public DepartmentBuilder addStudent(Student student) {
		this.department.getStudents().add(student);
		return this;
	}
	
	public DepartmentBuilder addProfessor(Professor professor) {
		this.department.getProfessors().add(professor);
		return this;
	}
	
	public DepartmentBuilder setAddress(Address address) {
		this.department.setAddress(address);
		return this;
	}

	@Override
	public Department build() {
		return this.department;
	}

	@Override
	public void clean() {
		this.department = new Department();
	}

}
