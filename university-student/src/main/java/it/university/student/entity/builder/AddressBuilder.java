package it.university.student.entity.builder;

import it.university.student.entity.Address;
import it.university.student.entity.Department;
import it.university.student.entity.Professor;
import it.university.student.entity.Student;

public class AddressBuilder implements Builder<Address>{
	
	private Address address;
	
	public AddressBuilder() {
		this.address = new Address();
	}
	
	public AddressBuilder setId(int id) {
		this.address.setId(id);
		return this;
	}
	
	public AddressBuilder setStreet(String street) {
		this.address.setStreet(street);
		return this;
	}
	
	public AddressBuilder setNumber(int number) {
		this.address.setNumber(number);
		return this;
	}

	public AddressBuilder setCity(String city) {
		this.address.setCity(city);
		return this;
	}
	
	public AddressBuilder setProvince(String province) {
		this.address.setProvince(province);
		return this;
	}
	
	public AddressBuilder setRegion(String region) {
		this.address.setRegion(region);
		return this;
	}
	
	public AddressBuilder setNation(String nation) {
		this.address.setNation(nation);
		return this;
	}
	
	public AddressBuilder addStudent(Student student) {
		this.address.getStudents().add(student);
		return this;
	}
	
	public AddressBuilder addProfessor(Professor professor) {
		this.address.getProfessors().add(professor);
		return this;
	}
	
	public AddressBuilder addDepartment(Department department) {
		this.address.getDepartments().add(department);
		return this;
	}
	
	@Override
	public Address build() {
		return address;
	}

	@Override
	public void clean() {
		this.address = new Address();
	}
}
