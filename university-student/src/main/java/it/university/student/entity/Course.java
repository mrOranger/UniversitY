package it.university.student.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity @Table(name = "Courses")
@Data @AllArgsConstructor @NoArgsConstructor
public class Course {
	
	@Id
	@NotNull(message = "{NotNull.Course.Id.Validation}")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	@NotNull(message = "{NotNull.Course.Name.Validation}")
	@Size(max = 30, message = "{Size.Course.Name.Validation}")
	private String name;
	
	@Column(name = "start_date", nullable = true)
	private Date date;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "course", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JsonBackReference
	@EqualsAndHashCode.Exclude
	private Set<Exam> exams = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Professor> professors = new HashSet<>();

}
