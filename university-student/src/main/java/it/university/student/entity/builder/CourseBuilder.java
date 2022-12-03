package it.university.student.entity.builder;

import java.sql.Date;

import it.university.student.entity.Course;

public class CourseBuilder implements Builder<Course>{
	
	private Course course;
	
	public CourseBuilder() {
		this.course = new Course();
	}
	
	public CourseBuilder setId(int id) {
		this.course.setId(id);
		return this;
	}
	
	public CourseBuilder setName(String name) {
		this.course.setName(name);
		return this;
	}
	
	public CourseBuilder setDate(Date date) {
		this.course.setDate(date);
		return this;
	}

	@Override
	public Course build() {
		return this.course;
	}

	@Override
	public void clean() {
		this.course = new Course();
	}
}
