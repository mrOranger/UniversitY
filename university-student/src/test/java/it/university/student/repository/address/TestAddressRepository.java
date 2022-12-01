package it.university.student.repository.address;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest @TestMethodOrder(OrderAnnotation.class)
public class TestAddressRepository {
	
	@Autowired private AddressRepository repository;
	
	
	@Test @Order(1)
	public void testGetAddresses() {
		final List<Address> addresses = this.repository.findAll();
		assertTrue(addresses.isEmpty());
	}
	
	@Test @Order(2)
	public void testGetAddress() {
		final Address address = this.repository.findById(1);
		assertThat(address).extracting(Address::getStreet).isEqualTo("Via Nazionale");
	}
	
	@Test @Order(3)
	public void testGetAddressFailure() {
		final Address address = this.repository.findById(10);
		assertNull(address);		
	}
	
	@Test @Order(4)
	public void testGetAddressByCity() {
		final List<Address> addresses = this.repository.findByStreet("Bari");
		assertEquals(addresses.size(), 1);
	}
	
	@Test @Order(5)
	public void testGetAddressByProvince() {
		final List<Address> addresses = this.repository.findByProvince("Bari");
		assertEquals(addresses.size(), 1);
	}
	
	@Test @Order(6)
	public void testGetAddressByRegion() {
		final List<Address> addresses = this.repository.findByRegion("Puglia");
		assertEquals(addresses.size(), 1);		
	}
	
	@Test @Order(7)
	public void testGetAddressByNation() {
		final List<Address> addresses = this.repository.findByRegion("Italia");
		assertEquals(addresses.size(), 2);		
	}
	
	@Test @Order(8)
	public void testGetAddressByStudent() {
		final Address address = this.repository.findByStudent("1234");
		assertThat(address).extracting(Address::getStreet).isEqualTo("Via Nazionale");
	}
	
	@Test @Order(9)
	public void testAddAddress() {
		final Address address = new Addess();
		address.setStreet("Via Garibaldi")
			.setNumber(7)
			.setCity("Milano")
			.setProvince("Milano")
			.setRegion("Lombardia")
			.setNation("Italia");
		assertThat(this.repository.save(address))
			.extracting(Address::getStreet)
			.isEqualTo("Via Garibaldi");
	}
	
	@Test @Order(10)
	public void testPutAddresses() {
		
		final Address address = new Addess();
		address.setStreet("Via Venezia")
			.setNumber(12)
			.setCity("Milano")
			.setProvince("Milano")
			.setRegion("Lombardia")
			.setNation("Italia");
		
		assertThat(this.repository.save(address))
			.extracting(Address::getStreet)
			.isEqualTo("Via Venezia");
	}
	
	@Test @Order(11)
	public void testPutAddress() {
		
		List<Address> addresses = new ArrayList<>();
		
		final Address address = new Addess();
		address.setStreet("Via Venezia")
			.setNumber(12)
			.setCity("Milano")
			.setProvince("Milano")
			.setRegion("Lombardia")
			.setNation("Italia");
		
		addresses.add(address);
		
		address = new Addess();
		address.setStreet("Via Milano")
			.setNumber(11)
			.setCity("Torino")
			.setProvince("Torino")
			.setRegion("Piemonte")
			.setNation("Italia");
		
		addresses.add(address);
		
		address = new Addess();
		address.setStreet("Square Dam")
			.setNumber(121)
			.setCity("Amsterdam")
			.setProvince("Amsterdam")
			.setRegion("Amsterdam")
			.setNation("Netherlands");
		
		addresses.add(address);
		
		assertThat(this.repository.saveAll(addresses), contains(
				hasProperty("street", is("Via Venezia")),
				hasProperty("street", is("Via Milano")),
				hasProperty("street", is("Square Dam"))
		));
	}
	
	@Test @Order(12)
	public void testDeleteAddresses() {
		this.repository.deleteAll();
		assertThat(this.repository.findById(1)).isNull();
	}
	
	@Test @Order(13)
	public void testDeleteAddress() {
		this.repository.delete(this.repository.findById(2));
		assertThat(this.repository.findById(2)).isNull();		
	}
}
