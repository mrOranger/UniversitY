package it.university.department.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity @Table(name = "professor")
@Data @AllArgsConstructor @NoArgsConstructor
public class Professor implements Serializable {

	private static final long serialVersionUID = 433865263822636447L;
	
	@Id @Column(name = "id")
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
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "department")
	@EqualsAndHashCode.Exclude
	private Department worksIn;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "director")
	@EqualsAndHashCode.Exclude
	private Department director;
}
