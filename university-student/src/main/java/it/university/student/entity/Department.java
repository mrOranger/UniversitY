package it.university.student.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name = "Departments")
@Data @AllArgsConstructor @NoArgsConstructor
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
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "department")
	private Set<Professor> professors = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "department", referencedColumnName = "name")
	private Faculty faculty; 

}
