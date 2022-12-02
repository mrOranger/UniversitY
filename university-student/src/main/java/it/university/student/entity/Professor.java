package it.university.student.entity;

import java.util.Set;

public class Professor {
	
	private String id;
	private String name;
	private String surname;
	private String dateOfBirth;
	
	private Address address;
	private Set<Department> department;

}
