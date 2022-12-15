package it.university.department.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public final class AddressDTO {
	
	private int id;
	private String street;
	private int number;
	private String city;
	private String province;
	private String region;
	private String nation;
}
