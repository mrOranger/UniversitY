package it.university.department.entity.builder;

public interface Builder<T> {
	
	public void clean();
	public abstract T build();

}