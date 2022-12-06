package it.university.student.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity @Table(name = "Exams")
@Data @AllArgsConstructor @NoArgsConstructor
public class Exam {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private int id;
	
	@Column(name = "date")
	@NotNull(message = "{NotNull.Exam.Date.Validation}")
	private Date date;
	
	@Column(name = "vote", nullable = true)
	@Min(value = (byte)0, message = "{Min.Exam.Date.Validation}")
	@Max(value = (byte)30, message = "{Max.Exam.Date.Validation}")
	private byte vote;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Student.class)
	@JoinColumn(name = "student", referencedColumnName = "id", updatable = true, insertable = true)
	@EqualsAndHashCode.Exclude
	private Student student;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "course", referencedColumnName = "id")
	private Course course;
	
}
