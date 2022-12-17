package it.university.faculty.dao.service.converter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import it.university.faculty.dto.Faculty;
import it.university.faculty.entity.FacultyEntity;

public final class FacultyConverter {
	
	@Autowired private ModelMapper modelMapper;

	public final Optional<Faculty> convertToDto(Optional<FacultyEntity> faculty) {
		if(faculty.isPresent()) {
			return Optional.of(modelMapper.map(faculty.get(), Faculty.class));
		}
		return Optional.empty();
	}
	
	public final List<Faculty> convertToDto(List<FacultyEntity> faculties){
		return faculties.stream().map(this::mapEntityToDto).collect(Collectors.toList());
	}
	
	private final Faculty mapEntityToDto(FacultyEntity entity) {
		return modelMapper.map(entity, Faculty.class);
	}
}
