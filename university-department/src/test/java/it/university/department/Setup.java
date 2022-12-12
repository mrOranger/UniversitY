package it.university.department;

import org.junit.jupiter.api.BeforeEach;

public interface Setup {

	@BeforeEach
	public abstract void setup();
	
}
