package it.university.faculty.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public final class Address {
	
	private Integer id;
	private String street;
	private Integer number;
	private String city;
	private String province;
	private String region;
	private String nation;
}
