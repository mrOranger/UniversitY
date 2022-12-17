package it.university.faculty.entity;

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

@Entity @Table(name = "faculty") 
@Data @AllArgsConstructor @NoArgsConstructor
public final class FacultyEntity implements Serializable {

	private static final long serialVersionUID = 2429376661192847168L;

	@Id @Column(name = "name")
	@NotNull(message = "{Faculty.Name.NotNull}")
	private String name;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "address", referencedColumnName = "id")
	@EqualsAndHashCode.Exclude
	private AddressEntity address;
}
