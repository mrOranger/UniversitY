package it.university.student.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name = "Students")
@Data @AllArgsConstructor @NoArgsConstructor
public class Student {
	
	@Id @Column(name = "Id")
	@Size(min = 5, max = 15, message = "{Size.Student.Id.Validation}")
	@NotNull(message = "{NotNull.Student.Id.Validation}")
	private String id;
	
	@Column(name = "name") 
	@Size(min = 10, max = 30, message = "{Size.Student.Name.Validation}")
	@NotNull(message = "{NotNull.Student.Name.Validation}")
	private String name;
	
	@Column(name = "surname")
	@Size(min = 10, max = 30, message = "{Size.Student.Surname.Validation}")
	@NotNull(message = "{NotNull.Student.Surname.Validation}")
	private String surname;
	
	@Column(name = "date_of_birth")
	@Temporal(TemporalType.DATE)
	@NotNull(message = "{NotNull.Student.DateOfBirth.Validation}")
	private LocalDate dateOfBirth;
	
	@Column(name = "sex")
	private char sex;
	
	@Column(name = "diploma_grade")
	@NotNull(message = "{NotNull.Student.DiplomaGrade.Validation}")
	@Min(value = (byte)60, message = "{Min.Student.DiplomaGrade.Validation}") 
	@Max(value = (byte)100, message = "{Min.Student.DiplomaGrade.Validation}")
	private byte diplomaGrade;
	
	@Column(name = "bachelor_grade")
	@Min(value = (byte)66, message = "{Min.Student.BachelorGrade.Validation}") 
	@Max(value = (byte)110, message = "{Max.Student.BachelorGrade.Validation}")
	private byte bachelorGrade;
	
	@ManyToOne
	@JoinColumn(name = "address", referencedColumnName = "id")
	private Address address;
	
	@ManyToOne
	@JoinColumn(name = "department", referencedColumnName = "id")
	private Department department;

}