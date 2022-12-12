package it.university.department.repository.department;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.university.department.Setup;
import it.university.department.dao.impl.DepartmentService;
import it.university.department.dto.DepartmentDTO;
import it.university.department.entity.Department;
import it.university.department.entity.builder.AddressBuilder;
import it.university.department.entity.builder.DepartmentBuilder;
import it.university.department.entity.builder.FacultyBuilder;
import it.university.department.entity.builder.StudentBuilder;

@SpringBootTest @TestMethodOrder(OrderAnnotation.class) @Order(3)
public class UpdateTest implements Setup {
	
	@Autowired private DepartmentService departmentService;
	
	@Autowired private DepartmentBuilder departmentBuilder;
	@Autowired private AddressBuilder addressBuilder;
	@Autowired private StudentBuilder studentBuilder;
	@Autowired private FacultyBuilder facultyBuilder;
	
	private Department department;
	
	@Override
	public void setup() {
		this.department = this.departmentBuilder
				.setName("Informatica & TPS")
				.setAddress(this.addressBuilder.setId(12)
					.setStreet("Via delle Calendule")
					.setNumber(91)
					.setCity("Foggia")
					.setProvince("Foggia")
					.setRegion("Puglia")
					.setNation("Italia")
					.build())
				.setFaculty(this.facultyBuilder
						.setName("IMF")
						.setAddress(this.addressBuilder.setId(12)
								.setStreet("Via delle Calendule")
								.setNumber(91)
								.setCity("Foggia")
								.setProvince("Foggia")
								.setRegion("Puglia")
								.setNation("Italia")
								.build())
						.build())
				.addStudent(this.studentBuilder.setId("OK901")
						.setName("Ludovica")
						.setSurname("Magalli")
						.setDateOfBirth(Date.valueOf(LocalDate.parse("1998-10-09")))
						.setDiplomaGrade(89)
						.setBachelorGrade(100)
						.setAddress(this.addressBuilder.setId(11)
								.setStreet("Via delle Calende")
								.setNumber(90)
								.setCity("Foggia")
								.setProvince("Foggia")
								.setRegion("Puglia")
								.setNation("Italia")
								.build())
						.build())
				.build();		
	}
	
	@Test @Order(1)
	public void updateDepartment() {
		this.departmentService.save(this.department);
		
		assertThat(this.departmentService.findById("Informatica & TPS"))
			.extracting(DepartmentDTO::getName)
			.isEqualTo(this.department.getName());
	}
	
	@Test @Order(2)
	public void updateDepartmentWithFailure() {
		this.department.getAddress().setCity(null);
		assertThrows(IllegalArgumentException.class, () -> this.departmentService.save(this.department));
	}
	
	@Test @Order(3)
	public void updateDepartmentNullWithFailure() {
		assertThrows(IllegalArgumentException.class, () -> this.departmentService.save(null));
	}
}
