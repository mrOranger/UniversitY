package it.university.department.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public final class DepartmentDTO {
	
	private String name;
	private AddressDTO address;
	private FacultyDTO faculty;
	
}
