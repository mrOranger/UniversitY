package it.university.faculty.respository.faculty;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.university.faculty.dao.service.FacultyService;
import it.university.faculty.entity.AddressEntity;
import it.university.faculty.entity.FacultyEntity;

@SpringBootTest @TestMethodOrder(OrderAnnotation.class) @Order(1)
public final class TestInsertFaculty {
	
	@Autowired private FacultyService facultyService;
	private FacultyEntity faculty;
	private AddressEntity address;
	
	@BeforeEach
	public void setup() {
		faculty = new FacultyEntity();
		address = new AddressEntity();
		
		faculty.setName("Prova");
		
		address.setId(99);
		address.setStreet("Via prova");
		address.setNumber(99);
		address.setCity("Prova");
		address.setProvince("Prova");
		address.setRegion("Prova");
		address.setNation("Prova");
		address.getDepartments().add(faculty);
		
		faculty.setAddress(address);
	}
	
	@Test @Order(1)
	public final void testInsertFaculty() {
		assertThat(facultyService.save(faculty))
			.extracting(FacultyEntity::getName)
			.isEqualTo(faculty.getName());
	}
	
	@Test @Order(2)
	public final void testInsertFacultyThrowsDuplicateException() {
		assertThrows(DuplicateException.class, () -> facultyService.save(faculty));
	}
	
	@Test @Order(3)
	public final void testInsertFacultyThrowsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> facultyService.save(null));
	}
	
	@Test @Order(4)
	public final void testInsertFacultyThrowsBadRequestExceptionForFacultyName() {
		faculty.setName(null);
		assertThrows(BadRequestException.class, () -> facultyService.save(faculty));
	}
	
	@Test @Order(5)
	public final void testInsertFacultyThrowsBadRequestExceptionForAddressId() {
		address.setId(null);
		assertThrows(BadRequestException.class, () -> facultyService.save(faculty));
		address.setId(99);
	}
	
	@Test @Order(6)
	public final void testInsertFacultyThrowsBadRequestExceptionForAddressStreet() {
		address.setStreet(null);
		assertThrows(BadRequestException.class, () -> facultyService.save(faculty));
		address.setStreet("Prova");
	}
	
	@Test @Order(7)
	public final void testInsertFacultyThrowsBadRequestExceptionForAddressNumber() {
		address.setNumber(null);
		assertThrows(BadRequestException.class, () -> facultyService.save(faculty));
		address.setNumber(99);
	}
	
	@Test @Order(8)
	public final void testInsertFacultyThrowsBadRequestExceptionForAddressCity() {
		address.setCity(null);
		assertThrows(BadRequestException.class, () -> facultyService.save(faculty));
		address.setCity(null);
	}
	
	@Test @Order(9)
	public final void testInsertFacultyThrowsBadRequestExceptionForAddressNation() {
		address.setNation(null);
		assertThrows(BadRequestException.class, () -> facultyService.save(faculty));
		address.setNation("Prova");
	}
	
	@Test @Order(10)
	public final void testInsertFacultyThrowsBadRequestExceptionForStreetSize() {
		address.setStreet("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
		assertThrows(BadRequestException.class, () -> facultyService.save(faculty));
		address.setStreet("Prova");
	}
	
	@Test @Order(11)
	public final void testInsertFacultyThrowsBadRequestExceptionForCitySize() {
		address.setCity("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
		assertThrows(BadRequestException.class, () -> facultyService.save(faculty));
		address.setCity("Prova");		
	}
	
	@Test @Order(12)
	public final void testInsertFacultyThrowsBadRequestExceptionForProvinceSize() {
		address.setProvince("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
		assertThrows(BadRequestException.class, () -> facultyService.save(faculty));
		address.setProvince("Prova");		
	}
	
	@Test @Order(13)
	public final void testInsertFacultyThrowsBadRequestExceptionForRegionSize() {
		address.setRegion("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
		assertThrows(BadRequestException.class, () -> facultyService.save(faculty));
		address.setRegion("Prova");		
	}
	
	@Test @Order(14)
	public final void testInsertFacultyThrowsBadRequestExceptionForNationSize() {
		address.setNation("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
		assertThrows(BadRequestException.class, () -> facultyService.save(faculty));
		address.setNation("Prova");		
	}
	
	@AfterEach
	public void dismiss() {
		facultyService.delete("Prova");
	}
}
