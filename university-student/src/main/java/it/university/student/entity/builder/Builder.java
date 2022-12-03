package it.university.student.entity.builder;

public interface Builder<T> {
	
	public void clean();
	public abstract T build();

}
