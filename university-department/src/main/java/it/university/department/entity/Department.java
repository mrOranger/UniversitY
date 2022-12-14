package it.university.department.entity;

import java.io.Serializable;

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

@Entity @Table(name = "department")
@Data @AllArgsConstructor @NoArgsConstructor
public class Department implements Serializable {

	private static final long serialVersionUID = 4255798146306630879L;
	
	@Id @Column(name = "name")
	@NotNull(message = "{NotNull.Department.Name.Validation}")
	private String name;
	
	@JsonBackReference(value = "department-address")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "address", referencedColumnName = "id")
	@EqualsAndHashCode.Exclude
	private Address address;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "faculty", referencedColumnName = "name")
	@EqualsAndHashCode.Exclude
	private Faculty faculty;
}
