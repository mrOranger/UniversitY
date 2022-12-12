package it.university.department.dao.impl.converter;

import java.util.List;

public interface Converter<F, T> {

	public abstract T convertToDto(F f);
	public abstract List<T> convertToDto(List<F> f);
}
