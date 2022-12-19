package it.university.faculty.respository.faculty;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.transaction.TransactionSystemException;

import it.university.faculty.dao.service.FacultyService;
import it.university.faculty.entity.AddressEntity;
import it.university.faculty.entity.FacultyEntity;

@SpringBootTest @TestMethodOrder(OrderAnnotation.class) @Order(3)
public final class TestUpdateFaculty {
	
	@Autowired private FacultyService facultyService;
	private FacultyEntity faculty;
	private AddressEntity address;
	
	@BeforeEach
	public void setup() {
		faculty = new FacultyEntity();
		address = new AddressEntity();
		
		faculty.setName("Test");
		
		address.setId(99);
		address.setStreet("Test");
		address.setNumber(99);
		address.setCity("Test");
		address.setProvince("Test");
		address.setRegion("Test");
		address.setNation("Test");
		address.getDepartments().add(faculty);
		
		faculty.setAddress(address);
	}
	
	@Test @Order(1)
	public final void testInserDummyFaculty() {
		assertThat(facultyService.save(faculty).isPresent());
	}
	
	@Test @Order(2)
	public final void testUpdateDummyFacultyAddress() {
		address.setCity("New Test");
		assertThat(facultyService.save(faculty).get().getAddress().getCity().equals("New Test"));
	}
	
	@Test @Order(3)
	public final void testUpdateDummyFacultyThrowsExceptionForFacultyNull() {
		assertThrows(InvalidDataAccessApiUsageException.class, () -> facultyService.save(null));
	}
	
	@Test @Order(4)
	public final void testUpdateDummyFacultyThrowsExceptionForFacultyName() {
		faculty.setName(null);
		assertThrows(JpaSystemException.class, () -> facultyService.save(faculty));
	}
	
	@Test @Order(5)
	public final void testUpdateDummyFacultyThrowsExceptionForAddressIdNull() {
		address.setId(null);
		assertThrows(JpaSystemException.class, () -> facultyService.save(faculty));
		address.setId(99);
	}
	
	@Test @Order(6)
	public final void testUpdateDummyFacultyThrowsExceptionForAddressStreetNull() {
		address.setStreet(null);
		assertThrows(TransactionSystemException.class, () -> facultyService.save(faculty));
		address.setStreet("Test");
	}
	
	@Test @Order(7)
	public final void testUpdateDummyFacultyThrowsExceptionForAddressNumberNull() {
		address.setNumber(null);
		assertThrows(TransactionSystemException.class, () -> facultyService.save(faculty));
		address.setNumber(99);
	}
	
	@Test @Order(8)
	public final void testUpdateDummyFacultyThrowsExceptionForAddressCityNull() {
		address.setCity(null);
		assertThrows(TransactionSystemException.class, () -> facultyService.save(faculty));
		address.setCity("Test");
	}
	
	@Test @Order(9)
	public final void testUpdateDummyFacultyThrowsExceptionForAddressNationNull() {
		address.setNation(null);
		assertThrows(TransactionSystemException.class, () -> facultyService.save(faculty));
		address.setNation("Test");
	}
	
	@Test @Order(10)
	public final void testUpdateDummyFacultyThrowsExceptionForAddressStreetSize() {
		address.setStreet("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
		assertThrows(TransactionSystemException.class, () -> facultyService.save(faculty));
		address.setStreet("Test");
	}
	
	@Test @Order(11)
	public final void testUpdateDummyFacultyThrowsExceptionForAddressCitySize() {
		address.setCity("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
		assertThrows(TransactionSystemException.class, () -> facultyService.save(faculty));
		address.setCity("Test");	
	}
	
	@Test @Order(12)
	public final void testUpdateDummyFacultyThrowsExceptionForAddressProvinceSize() {
		address.setProvince("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
		assertThrows(TransactionSystemException.class, () -> facultyService.save(faculty));
		address.setProvince("Test");	
	}
	
	@Test @Order(13)
	public final void testUpdateDummyFacultyThrowsExceptionForAddressRegionSize() {
		address.setRegion("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
		assertThrows(TransactionSystemException.class, () -> facultyService.save(faculty));
		address.setRegion("Test");		
	}
	
	@Test @Order(14)
	public final void testUpdateDummyFacultyThrowsExceptionForAddressNationSize() {
		address.setNation("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
		assertThrows(TransactionSystemException.class, () -> facultyService.save(faculty));
		address.setNation("Test");		
	}
	
	@Test @Order(15)
	public final void testDeleteDummyFaculty() {
		facultyService.delete("Test");
		assertThat(facultyService.findById("Test").isEmpty());
	}
}