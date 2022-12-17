package it.university.faculty.respository.faculty;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;

import it.university.faculty.dao.service.FacultyService;
import it.university.faculty.entity.AddressEntity;
import it.university.faculty.entity.FacultyEntity;

public final class TestInsertFaculty {
	
	@Autowired private FacultyService facultyService;
	private FacultyEntity faculty;
	private AddressEntity address;
	
	@BeforeAll
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
	
	public final void testInsertFaculty() {
		assertThat(facultyService.save(faculty))
			.extracting(FacultyEntity::getName)
			.isEqualTo(faculty.getName());
	}
	
	public final void testInsertFacultyThrowsDuplicateException() {
		assertThrows(DuplicateException.class, () -> facultyService.save(faculty));
	}
	
	public final void testInsertFacultyThrowsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> facultyService.save(null));
	}
	
	public final void testInsertFacultyThrowsBadRequestExceptionForFacultyName() {
		faculty.setName(null);
		assertThrows(BadRequestException.class, () -> facultyService.save(faculty));
	}
	
	public final void testInsertFacultyThrowsBadRequestExceptionForAddressId() {
		address.setId(null);
		assertThrows(BadRequestException.class, () -> facultyService.save(faculty));
		address.setId(99);
	}
	
	public final void testInsertFacultyThrowsBadRequestExceptionForAddressStreet() {
		address.setStreet(null);
		assertThrows(BadRequestException.class, () -> facultyService.save(faculty));
		address.setStreet("Prova");
	}
	
	public final void testInsertFacultyThrowsBadRequestExceptionForAddressNumber() {
		address.setNumber(null);
		assertThrows(BadRequestException.class, () -> facultyService.save(faculty));
		address.setNumber(99);
	}
	
	public final void testInsertFacultyThrowsBadRequestExceptionForAddressCity() {
		address.setCity(null);
		assertThrows(BadRequestException.class, () -> facultyService.save(faculty));
		address.setCity(null);
	}
	
	public final void testInsertFacultyThrowsBadRequestExceptionForAddressNation() {
		address.setNation(null);
		assertThrows(BadRequestException.class, () -> facultyService.save(faculty));
		address.setNation("Prova");
	}
	
	public final void testInsertFacultyThrowsBadRequestExceptionForStreetSize() {
		address.setStreet("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
		assertThrows(BadRequestException.class, () -> facultyService.save(faculty));
		address.setStreet("Prova");
	}
	
	public final void testInsertFacultyThrowsBadRequestExceptionForCitySize() {
		address.setCity("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
		assertThrows(BadRequestException.class, () -> facultyService.save(faculty));
		address.setCity("Prova");		
	}
	
	public final void testInsertFacultyThrowsBadRequestExceptionForProvinceSize() {
		address.setProvince("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
		assertThrows(BadRequestException.class, () -> facultyService.save(faculty));
		address.setProvince("Prova");		
	}
	
	public final void testInsertFacultyThrowsBadRequestExceptionForRegionSize() {
		address.setRegion("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
		assertThrows(BadRequestException.class, () -> facultyService.save(faculty));
		address.setRegion("Prova");		
	}
	
	public final void testInsertFacultyThrowsBadRequestExceptionForNationSize() {
		address.setNation("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
		assertThrows(BadRequestException.class, () -> facultyService.save(faculty));
		address.setNation("Prova");		
	}
	
	@AfterAll
	public void dismiss() {
		facultyService.delete("Prova");
	}
}
