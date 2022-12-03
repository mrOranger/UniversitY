package it.university.student.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity @Table(name = "Professors")
@Data @AllArgsConstructor @NoArgsConstructor
public class Professor implements Serializable {
	
	private static final long serialVersionUID = -4714224185426689915L;

	@Id
	@NotNull(message = "{NotNull.Professor.Id.Validation}")
	@Size(max = 15, message = "{Size.Professor.Id.Validation}")
	private String id;
	
	@Column(name = "name") 
	@Size(max = 30, message = "{Size.Professor.Name.Validation}")
	@NotNull(message = "{NotNull.Professor.Name.Validation}")
	private String name;
	
	@Column(name = "surname")
	@Size(max = 30, message = "{Size.Professor.Surname.Validation}")
	@NotNull(message = "{NotNull.Professor.Surname.Validation}")
	private String surname;
	
	@Column(name = "date_of_birth")
	@Temporal(TemporalType.DATE)
	@NotNull(message = "{NotNull.Professor.DateOfBirth.Validation}")
	private Date dateOfBirth;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address")
	@EqualsAndHashCode.Exclude
	private Address address;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department")
	@JsonBackReference
	@EqualsAndHashCode.Exclude
	private Department department;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_director")
	@JsonBackReference
	@EqualsAndHashCode.Exclude
	private Department director;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "faculty_director")
	@JsonBackReference
	private Faculty faculty;
	
	@ManyToMany
	@JoinTable(
			name = "join_course",
			joinColumns = { @JoinColumn(name = "professor") },
			inverseJoinColumns = { @JoinColumn(name = "course") })
	@JsonBackReference
	private Set<Course> courses = new HashSet<>();
}
