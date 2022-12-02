package it.university.student.entity;

import java.time.LocalDate;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name = "Professors")
@Data @AllArgsConstructor @NoArgsConstructor
public class Professor {
	
	@Id
	@Column(name = "id")
	@NotNull(message = "{NotNull.Professor.Id.Validation}")
	@Size(min = 5, max = 15, message = "{Size.Professor.Id.Validation}")
	private String id;
	
	@Column(name = "name") 
	@Size(min = 10, max = 30, message = "{Size.Professor.Name.Validation}")
	@NotNull(message = "{NotNull.Professor.Name.Validation}")
	private String name;
	
	@Column(name = "surname")
	@Size(min = 10, max = 30, message = "{Size.Professor.Surname.Validation}")
	@NotNull(message = "{NotNull.Professor.Surname.Validation}")
	private String surname;
	
	@Column(name = "date_of_birth")
	@Temporal(TemporalType.DATE)
	@NotNull(message = "{NotNull.Professor.DateOfBirth.Validation}")
	private LocalDate dateOfBirth;
	
	@ManyToOne
	@JoinColumn(name = "address", referencedColumnName = "id")
	private Address address;
	
	@ManyToOne
	@JoinColumn(name = "department", referencedColumnName = "id")
	private Department department;
	
	@OneToOne(mappedBy = "direttore", cascade = CascadeType.ALL, orphanRemoval = true)
	private Department director;

}
