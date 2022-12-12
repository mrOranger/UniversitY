package it.university.department.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name = "faculty") 
@Data @AllArgsConstructor @NoArgsConstructor
public class Faculty implements Serializable {

	private static final long serialVersionUID = -497193358975253887L;
	
	@Id @Column(name = "name")
	@NotNull(message = "{NotNull.Faculty.Name.Validation}")
	private String name;
	
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "faculty", orphanRemoval = true)
	private Set<Department> departments = new HashSet<>();
}
