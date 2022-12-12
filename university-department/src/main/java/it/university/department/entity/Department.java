package it.university.department.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity @Table(name = "department")
@Data @AllArgsConstructor @NoArgsConstructor
public class Department implements Serializable {

	private static final long serialVersionUID = 4255798146306630879L;
	
	@Id @Column(name = "name")
	@NotNull(message = "{NotNull.Department.Name.Validation}")
	private String name;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "address", referencedColumnName = "id")
	@EqualsAndHashCode.Exclude
	private Address address;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "faculty", referencedColumnName = "name")
	@EqualsAndHashCode.Exclude
	private Faculty faculty;
	
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "worksIn")
	private Set<Professor> professors = new HashSet<>();

	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "director")
	private Set<Professor> directors = new HashSet<>();

	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "join_department", 
		joinColumns = { @JoinColumn(name = "department", referencedColumnName = "name") },
		inverseJoinColumns = { @JoinColumn(name = "student", referencedColumnName = "id") }) 
	private Set<Student> students = new HashSet<>();
}
