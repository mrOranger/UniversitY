package it.university.department.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
}
