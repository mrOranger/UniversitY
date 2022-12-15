package it.university.department.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.university.department.dao.ProfessorDAO;
import it.university.department.dao.impl.converter.Converter;
import it.university.department.dto.ProfessorDTO;
import it.university.department.entity.Professor;
import it.university.department.repository.ProfessorRepository;

@Service @Transactional(readOnly = true)
@CacheConfig(cacheNames = { "professori" })
public class ProfessorService implements ProfessorDAO, Converter<Professor, ProfessorDTO> {
	
	@Autowired private ProfessorRepository professorRepository;
	@Autowired private ModelMapper modelMapper;

	@Override @Cacheable(value = "professori.department", key = "#department", sync = true)
	public List<ProfessorDTO> findAllByDepartment(String department) {
		return this.convertToDto(this.professorRepository.findByWorksInName(department));
	}

	@Override @Cacheable(value = "professori.director.department", key = "#department", sync = true)
	public List<ProfessorDTO> findDirectorsByDepartment(String department) {
		return this.convertToDto(this.professorRepository.findByDirectorName(department));
	}

	@Override
	public ProfessorDTO convertToDto(Professor f) {
		ProfessorDTO studentDto = null;
		if(f != null) {
			studentDto = this.modelMapper.map(f, ProfessorDTO.class);
		}
		return studentDto;
	}

	@Override
	public List<ProfessorDTO> convertToDto(List<Professor> f) {
		return f.stream()
				.map((source) -> this.modelMapper.map(source, ProfessorDTO.class))
				.collect(Collectors.toList());
	}
}