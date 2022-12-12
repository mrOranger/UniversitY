package it.university.department.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name = "address")
@Data @AllArgsConstructor @NoArgsConstructor
public class Address implements Serializable{
	
	private static final long serialVersionUID = -3040216250095742667L;

	@Id 
	@Column(name = "id")
	@NotNull(message = "{NotNull.Address.Id.Validation}")
	private int id;
	
	@Column(name = "street")
	@Size(max = 50, message = "Size.Address.Street.Validation")
	@NotNull(message = "{NotNull.Address.Street.Validation}")
	private String street;

	@Column(name = "number")
	@NotNull(message = "{NotNull.Address.Number.Validation}")
	private int number;
	
	@Column(name = "city")
	@NotNull(message = "{NotNull.Address.City.Validation}")
	@Size(max = 50, message = "{Size.Address.City.Validation}")
	private String city;
	
	@Column(name = "province")
	@Size(max = 50, message = "{Size.Address.Province.Validation}")
	private String province;
	
	@Column(name = "region")
	@Size(max = 50, message = "{Size.Address.Region.Validation}")
	private String region;
	
	@Column(name = "nation")
	@Size(max = 50, message = "{Size.Address.Nation.Validation}")
	@NotNull(message = "{NotNull.Address.Nation.Validation}")
	private String nation;
	
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "address", orphanRemoval = true)
	private Set<Department> departments = new HashSet<>();

	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "address", orphanRemoval = true)
	private Set<Faculty> faculties = new HashSet<>();
	
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "address", orphanRemoval = true)
	private Set<Student> students = new HashSet<>();
}