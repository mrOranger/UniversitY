package it.university.department.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name = "professor")
@Data @AllArgsConstructor @NoArgsConstructor
public class Professor implements Serializable {

	private static final long serialVersionUID = 433865263822636447L;
	
	@Id @Column(name = "id")
	@NotNull(message = "{NotNull.Professor.Id.Validation}")
	private String id;
	
	@Column(name =  "name")
	@NotNull(message = "{NotNull.Professor.Name.Validation}")
	private String name;
	
	@Column(name = "surname")
	@NotNull(message = "{NotNull.Professor.Surname.Validation}")
	private String surname;
	
	@Column(name = "dateOfBirth")
	@NotNull(message = "{NotNull.Professor.DateOfBirth.Validation}")
	private Date dateOfBirth;
}