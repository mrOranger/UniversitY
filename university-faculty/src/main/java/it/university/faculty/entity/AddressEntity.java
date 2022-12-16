package it.university.faculty.entity;

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
public final class AddressEntity implements Serializable {
	
	private static final long serialVersionUID = -146115546931957518L;

	@Id 
	@Column(name = "id")
	@NotNull(message = "{Address.Id.NotNull}")
	private int id;
	
	@Column(name = "street")
	@Size(max = 50, message = "Address.Street.Size")
	@NotNull(message = "{Address.Street.NotNull}")
	private String street;

	@Column(name = "number")
	@NotNull(message = "{Address.Number.NotNull}")
	private int number;
	
	@Column(name = "city")
	@NotNull(message = "{Address.City.NotNull}")
	@Size(max = 50, message = "{Address.City.Size}")
	private String city;
	
	@Column(name = "province")
	@Size(max = 50, message = "{Address.Province.Size}")
	private String province;
	
	@Column(name = "region")
	@Size(max = 50, message = "{Address.Region.Size}")
	private String region;
	
	@Column(name = "nation")
	@Size(max = 50, message = "{Address.Nation.Size}")
	@NotNull(message = "{Address.Nation.NotNull}")
	private String nation;
	
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "address", orphanRemoval = true)
	private Set<FacultyEntity> departments = new HashSet<>();
}
