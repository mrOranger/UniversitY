package it.university.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public final class AddressDTO {
	
	private int id;
	private String street;
	private String number;
	private String city;
	private String province;
	private String region;
	private String nation;

}
