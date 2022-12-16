package it.university.faculty.respository.faculty;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest @TestMethodOrder(OrderAnnotation.class) @Order(1)
public final class TestSelectFaculty {
	
	@Autowired private FacultyService facultyService;
	
	@Test @Order(1)
	public final void testSelectFaculty() {
		assertThat(this.facultyService.findAll())
			.extracting(List::size)
			.isEqualTo(3);
	}
	
	@Test @Order(2)
	public final void testSelectFacultyNotFound() {
		assertNull(this.facultyService.findById("Medicina"));
	}
	
	@Test @Order(3)
	public final void testSelectFacultyByAddressCity() {
		assertThat(this.facultyService.findAllByCity("Milano"))
			.extracting(List::size)
			.isEqualTo(3);
	}
	
	@Test @Order(4)
	public final void testSelectFacultyByAddressCityNotFound() {
		assertThat(this.facultyService.findAllByCity("Napoli"))
			.extracting(List::size)
			.isEqualTo(0);
	}
	
	@Test @Order(5)
	public final void testSelectFacultyByAddressProvince() {
		assertThat(this.facultyService.findAllByProvince("Milano"))
			.extracting(List::size)
			.isEqualTo(2);
	}
	
	@Test @Order(6)
	public final void testSelectFacultyByAddressProvinceNotFound() {
		assertThat(this.facultyService.findAllByProvince("Napoli"))
			.extracting(List::size)
			.isEqualTo(0);		
	}
	
	@Test @Order(7)
	public final void testSelectFacultyByAddressRegion() {
		assertThat(this.facultyService.findAllByRegion("Lombardia"))
			.extracting(List::size)
			.isEqualTo(3);		
	}
	
	@Test @Order(8)
	public final void testSelectFacultyByAddressRegionNotFound() {
		assertThat(this.facultyService.findAllByRegion("Campania"))
			.extracting(List::size)
			.isEqualTo(0);		
	}
	
	@Test @Order(9)
	public final void testSelectFacultyByAddressNation() {
		assertThat(this.facultyService.findAllByNation("Italia"))
			.extracting(List::size)
			.isEqualTo(3);		
	}
	
	@Test @Order(10)
	public final void testSelectFacultyByAddressNationNotFound() {
		assertThat(this.facultyService.findAllByNation("USA"))
			.extracting(List::size)
			.isEqualTo(0);		
	}
}
