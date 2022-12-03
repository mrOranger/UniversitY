package it.university.student.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity @Table(name = "Departments")
@Data @AllArgsConstructor @NoArgsConstructor
public class Department implements Serializable{
	
	private static final long serialVersionUID = 2117670150489443646L;

	@Id @Column(name = "name")
	@NotNull(message = "{NotNull.Department.Name.Validation}")
	@Size(max = 15, message = "{Size.Department.Name.Validation}")
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address")
	private Address address;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "director")
	@PrimaryKeyJoinColumn
	private Professor director;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
	@JsonBackReference
	@EqualsAndHashCode.Exclude
	private Set<Student> students = new HashSet<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
	@JsonManagedReference
	private Set<Professor> professors = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "faculty")
	private Faculty faculty; 

}
