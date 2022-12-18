package it.university.faculty.respository.faculty;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
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
import lombok.SneakyThrows;

@SpringBootTest @TestMethodOrder(OrderAnnotation.class) @Order(2)
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
	
	@Test @Order(1) @SneakyThrows
	public final void testInsertFaculty() {
		assertThat(facultyService.save(faculty).isPresent());
	}
	
	@Test @Order(2)
	public final void testInsertFacultyThrowsIllegalArgumentException() {
		assertThrows(InvalidDataAccessApiUsageException.class, () -> facultyService.save(null));
	}
	
	@Test @Order(3)
	public final void testInsertFacultyThrowsBindingExceptionForFacultyName() {
		faculty.setName(null);
		assertThrows(JpaSystemException.class, () -> facultyService.save(faculty));
	}
	
	@Test @Order(4)
	public final void testInsertFacultyThrowsBindingExceptionForAddressId() {
		address.setId(null);
		assertThrows(JpaSystemException.class, () -> facultyService.save(faculty));
		address.setId(99);
	}
	
	@Test @Order(5)
	public final void testInsertFacultyThrowsBindingExceptionForAddressStreet() {
		address.setStreet(null);
		assertThrows(TransactionSystemException.class, () -> facultyService.save(faculty));
		address.setStreet("Prova");
	}
	
	@Test @Order(6)
	public final void testInsertFacultyThrowsBindingExceptionForAddressNumber() {
		address.setNumber(null);
		assertThrows(TransactionSystemException.class, () -> facultyService.save(faculty));
		address.setNumber(99);
	}
	
	@Test @Order(7)
	public final void testInsertFacultyThrowsBindingExceptionForAddressCity() {
		address.setCity(null);
		assertThrows(TransactionSystemException.class, () -> facultyService.save(faculty));
		address.setCity(null);
	}
	
	@Test @Order(8)
	public final void testInsertFacultyThrowsBindingExceptionForAddressNation() {
		address.setNation(null);
		assertThrows(TransactionSystemException.class, () -> facultyService.save(faculty));
		address.setNation("Prova");
	}
	
	@Test @Order(9)
	public final void testInsertFacultyThrowsBindingExceptionForStreetSize() {
		address.setStreet("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
		assertThrows(TransactionSystemException.class, () -> facultyService.save(faculty));
		address.setStreet("Prova");
	}
	
	@Test @Order(10)
	public final void testInsertFacultyThrowsBindingExceptionForCitySize() {
		address.setCity("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
		assertThrows(TransactionSystemException.class, () -> facultyService.save(faculty));
		address.setCity("Prova");		
	}
	
	@Test @Order(11)
	public final void testInsertFacultyThrowsBindingExceptionForProvinceSize() {
		address.setProvince("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
		assertThrows(TransactionSystemException.class, () -> facultyService.save(faculty));
		address.setProvince("Prova");		
	}
	
	@Test @Order(12)
	public final void testInsertFacultyThrowsBindingExceptionForRegionSize() {
		address.setRegion("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
		assertThrows(TransactionSystemException.class, () -> facultyService.save(faculty));
		address.setRegion("Prova");		
	}
	
	@Test @Order(13)
	public final void testInsertFacultyThrowsBindingExceptionForNationSize() {
		address.setNation("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
		assertThrows(TransactionSystemException.class, () -> facultyService.save(faculty));
		address.setNation("Prova");		
	}
	
	@Test @Order(14)
	public final void testDeleteInsertEntity() {
		facultyService.delete("Prova");
		assertThat(facultyService.findById("Prova").isEmpty());
	}
}
