package it.university.student.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name = "Addresses")
@Data @AllArgsConstructor @NoArgsConstructor
public class Address {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@NotNull(message = "{NotNull.Address.Id.Validation}")
	private int id;
	
	@Column(name = "street")
	@Size(min = 10, max = 50, message = "Size.Address.Street.Validation")
	@NotNull(message = "{NotNull.Address.Street.Validation}")
	private String street;

	@Column(name = "number")
	@NotNull(message = "{NotNull.Address.Number.Validation}")
	private int number;
	
	@Column(name = "city")
	@NotNull(message = "{NotNull.Address.City.Validation}")
	@Size(min = 5, max = 50, message = "{Size.Address.City.Validation}")
	private String city;
	
	@Column(name = "province")
	@Size(min = 5, max = 50, message = "{Size.Address.Province.Validation}")
	private String province;
	
	@Column(name = "region")
	@Size(min = 5, max = 50, message = "{Size.Address.Region.Validation}")
	private String region;
	
	@Column(name = "nation")
	@Size(min = 5, max = 50, message = "{Size.Address.Nation.Validation}")
	@NotNull(message = "{NotNull.Address.Nation.Validation}")
	private String nation;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "address")
	@JsonManagedReference
	private Set<Student> students = new HashSet<>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "address")
	@JsonManagedReference
	private Set<Professor> professors = new HashSet<>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "address")
	@JsonManagedReference
	private Set<Department> departments = new HashSet<>();

}
