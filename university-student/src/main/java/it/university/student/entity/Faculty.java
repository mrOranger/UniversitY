package it.university.student.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name = "Faculties")
@Data @AllArgsConstructor @NoArgsConstructor
public class Faculty implements Serializable {
	
	private static final long serialVersionUID = -7069931654707277600L;

	@Id @Column(name = "name")
	@NotNull(message = "{NotNull.Faculty.Name.Validation}")
	@Size(min = 5, max = 15, message = "{Size.Faculty.Name.Validation}")
	private String name;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	@JsonManagedReference
	private Professor director;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "faculty", orphanRemoval = true)
	@JsonManagedReference
	private Set<Department> departments = new HashSet<>();

}
