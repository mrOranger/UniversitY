package it.university.student.entity.builder;

import java.sql.Date;

import it.university.student.entity.Course;
import it.university.student.entity.Exam;

public class ExamBuilder implements Builder<Exam>{
	
	private Exam exam;

	public ExamBuilder() {
		this.exam = new Exam();
	}
	
	public ExamBuilder setId(int id) {
		this.exam.setId(id);
		return this;
	}
	
	public ExamBuilder setDate(Date date) {
		this.exam.setDate(date);
		return this;
	}
	
	public ExamBuilder setVote(byte vote) {
		this.exam.setVote(vote);
		return this;
	}
	
	public ExamBuilder setCourse(Course course) {
		this.exam.setCourse(course);
		return this;
	}

	@Override
	public Exam build() {
		return this.exam;
	}

	@Override
	public void clean() {
		this.exam = new Exam();
	}
}
