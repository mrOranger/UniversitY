package it.university.department.entity.builder;

import it.university.department.entity.Address;
import it.university.department.entity.Department;
import it.university.department.entity.Faculty;

public final class DepartmentBuilder implements Builder<Department> {
	
	private Department department;
	
	public DepartmentBuilder() {
		this.department = new Department();
	}
	
	public DepartmentBuilder setName(String name) {
		this.department.setName(name);
		return this;
	}
	
	public DepartmentBuilder setAddress(Address address) {
		this.department.setAddress(address);
		address.getDepartments().add(this.department);
		return this;
	}
	
	public DepartmentBuilder setFaculty(Faculty faculty) {
		this.department.setFaculty(faculty);
		faculty.getDepartments().add(this.department);
		return this;
	}

	@Override
	public void clean() {
		this.department = new Department();
	}

	@Override
	public Department build() {
		return this.department;
	}

}
