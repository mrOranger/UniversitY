package it.university.department.controller.address;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest @TestMethodOrder(OrderAnnotation.class) @Order(10)
public class DeleteTest {

}
