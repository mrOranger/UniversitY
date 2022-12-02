package it.university.student.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Department implements Serializable{
	
	private static final long serialVersionUID = 2117670150489443646L;

	@Id @Column(name = "name") 
	@NotNull(message = "{NotNull.Department.Name.Validation}")
	@Size(min = 5, max = 15, message = "{Size.Department.Name.Validation}")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "address", referencedColumnName = "id")
	private Address address;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	@JsonIgnore
	private Professor director;
	
	@ManyToOne
	@JoinColumn(name = "department", referencedColumnName = "name")
	private Faculty faculty; 

}
