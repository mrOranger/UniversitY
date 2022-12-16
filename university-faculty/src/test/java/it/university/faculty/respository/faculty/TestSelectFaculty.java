package it.university.faculty.respository.faculty;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.university.faculty.dao.service.FacultyService;
import it.university.faculty.dto.Faculty;


@SpringBootTest @TestMethodOrder(OrderAnnotation.class) @Order(1)
public final class TestSelectFaculty {
	
	@Autowired private FacultyService facultyService;
	
	@Test @Order(1)
	public final void testSelectFaculties() {
		assertThat(this.facultyService.findAll().size())
			.isEqualTo(3);
	}
	
	@Test @Order(2)
	public final void testSelectFaculty() {
		assertThat(this.facultyService.findById("Ingegneria"))
			.extracting(Faculty::getName)
			.isEqualTo("Ingegneria");
	}
	
	@Test @Order(3)
	public final void testSelectFacultyNotFound() {
		assertNull(this.facultyService.findById("Medicina"));
	}
	
	@Test @Order(4)
	public final void testSelectFacultyByAddressCity() {
		assertThat(this.facultyService.findAllByCity("Milano").size())
			.isEqualTo(1);
	}
	
	@Test @Order(5)
	public final void testSelectFacultyByAddressCityNotFound() {
		assertThat(this.facultyService.findAllByCity("Napoli").size())
			.isEqualTo(0);
	}
	
	@Test @Order(6)
	public final void testSelectFacultyByAddressProvince() {
		assertThat(this.facultyService.findAllByProvince("Milano").size())
			.isEqualTo(1);
	}
	
	@Test @Order(7)
	public final void testSelectFacultyByAddressProvinceNotFound() {
		assertThat(this.facultyService.findAllByProvince("Napoli").size())
			.isEqualTo(0);		
	}
	
	@Test @Order(8)
	public final void testSelectFacultyByAddressRegion() {
		assertThat(this.facultyService.findAllByRegion("Lombardia").size())
			.isEqualTo(1);		
	}
	
	@Test @Order(9)
	public final void testSelectFacultyByAddressRegionNotFound() {
		assertThat(this.facultyService.findAllByRegion("Campania").size())
			.isEqualTo(0);		
	}
	
	@Test @Order(10)
	public final void testSelectFacultyByAddressNation() {
		assertThat(this.facultyService.findAllByNation("Italia").size())
			.isEqualTo(3);		
	}
	
	@Test @Order(11)
	public final void testSelectFacultyByAddressNationNotFound() {
		assertThat(this.facultyService.findAllByNation("USA").size())
			.isEqualTo(0);		
	}
}
