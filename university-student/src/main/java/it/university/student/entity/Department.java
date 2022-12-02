package it.university.student.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Department {
	
	@Id @Column(name = "name") 
	@NotNull(message = "{NotNull.Department.Name.Validation}")
	@Size(min = 5, max = 15, message = "{Size.Department.Id.Validation}")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "address", referencedColumnName = "id")
	private Address address;
	
	@OneToOne(mappedBy = "director", cascade = CascadeType.ALL, orphanRemoval = true)
	private Professor director;
	
	@ManyToOne
	@JoinColumn(name = "department", referencedColumnName = "name")
	private Faculty faculty; 

}
