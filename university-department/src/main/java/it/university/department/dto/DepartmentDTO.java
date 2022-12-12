package it.university.department.dto;

import it.university.department.entity.Address;
import it.university.department.entity.Faculty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public final class DepartmentDTO {
	
	private String name;
	private Address address;
	private Faculty faculty;
	
}
