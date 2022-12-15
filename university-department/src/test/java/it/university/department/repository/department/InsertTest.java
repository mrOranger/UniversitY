package it.university.department.repository.department;

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
import org.springframework.transaction.TransactionSystemException;

import it.university.department.Setup;
import it.university.department.dao.impl.DepartmentService;
import it.university.department.dto.DepartmentDTO;
import it.university.department.entity.Address;
import it.university.department.entity.Department;
import it.university.department.entity.Faculty;
import it.university.department.entity.builder.AddressBuilder;
import it.university.department.entity.builder.DepartmentBuilder;
import it.university.department.entity.builder.FacultyBuilder;

@SpringBootTest @TestMethodOrder(OrderAnnotation.class) @Order(2)
public class InsertTest implements Setup{
	
	@Autowired private DepartmentService departmentService;
	
	@Autowired private DepartmentBuilder departmentBuilder;
	@Autowired private AddressBuilder addressBuilder;
	@Autowired private FacultyBuilder facultyBuilder;
	
	private Department department;
	
	@Override @BeforeEach
	public void setup() {
		
		final Address address = this.addressBuilder.setId(4)
				.setStreet("Via Nazionale")
				.setNumber(4)
				.setCity("Palermo")
				.setProvince("Palermo")
				.setRegion("Sicilia")
				.setNation("Italia")
				.build();
		
		final Faculty faculty = this.facultyBuilder
				.setName("IMF")
				.build();
		
		this.department = this.departmentBuilder
				.setName("Informatica & TPS")
				.setAddress(address)
				.setFaculty(faculty)
				.build();
	}

	@Test @Order(1)
	public void addDepartment() {
		
		this.departmentService.save(this.department);
		
		assertThat(this.departmentService.findById("Informatica & TPS"))
			.extracting(DepartmentDTO::getName)
			.isEqualTo(this.department.getName());
	}
	
	@Test @Order(2)
	public void addDepartmentWithFailure() {
		this.department.getAddress().setCity(null);
		assertThrows(TransactionSystemException.class, () -> this.departmentService.save(this.department));
	}
	
	@Test @Order(3)
	public void addDepartmentNullWithFailure() {
		assertThrows(InvalidDataAccessApiUsageException.class, () -> this.departmentService.save(null));
	}
}
