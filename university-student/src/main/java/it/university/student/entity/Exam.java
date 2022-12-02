package it.university.student.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
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
	
	@Column(name = "vote")
	@Min(value = (byte)0, message = "{Min.Exam.Date.Validation}")
	@Max(value = (byte)30, message = "{Max.Exam.Date.Validation}")
	private byte vote;
	
	@ManyToOne
	@JoinColumn(name = "student", referencedColumnName = "id")
	private Student student;
	
}
