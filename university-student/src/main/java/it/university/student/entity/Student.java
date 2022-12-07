package it.university.student.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity @Table(name = "student")
@Data @AllArgsConstructor @NoArgsConstructor
public class Student implements Serializable {
	
	private static final long serialVersionUID = 5616681937140085884L;

	@Id 
	@Column(name = "id")
	@Size(max = 20, message = "{Size.Student.Id.Validation}")
	@NotNull(message = "{NotNull.Student.Id.Validation}")
	private String id;
	
	@Column(name = "name") 
	@Size(max = 30, message = "{Size.Student.Name.Validation}")
	@NotNull(message = "{NotNull.Student.Name.Validation}")
	private String name;
	
	@Column(name = "surname")
	@Size(max = 30, message = "{Size.Student.Surname.Validation}")
	@NotNull(message = "{NotNull.Student.Surname.Validation}")
	private String surname;
	
	@Column(name = "dateOfBirth")
	@Temporal(TemporalType.DATE)
	@NotNull(message = "{NotNull.Student.DateOfBirth.Validation}")
	private Date dateOfBirth;
	
	@Column(name = "diplomaGrade")
	@NotNull(message = "{NotNull.Student.DiplomaGrade.Validation}")
	private Integer diplomaGrade;
	
	@Column(name = "bachelorGrade")
	private Integer bachelorGrade;
	
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "address", referencedColumnName = "id")
	@EqualsAndHashCode.Exclude
	private Address address;

}
