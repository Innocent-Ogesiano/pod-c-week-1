package org.java.servicesImpl;

import org.java.enums.Gender;
import org.java.enums.Roles;
import org.java.models.Company;
import org.java.models.Employee;
import org.java.models.Product;
import org.java.services.ManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerServiceImplTest {
    private Company company;
    private ManagerService managerService;

    @BeforeEach
    void setUp() {
        company = new Company("Decagon");
        managerService = new ManagerServiceImpl(company);
    }

    @Test
    void successfullyAddNewProductToStock() {
        Employee manager = new Employee("Ernest", "09900", Roles.MANAGER, 50000.0, 1, Gender.MALE);
        String value = managerService.addProductToStock("MacBook Laptop", 900000.0, 5, manager);
        System.out.println(value);
        assertNotNull(value);
        assertEquals("new product successfully added", value);
        assertEquals(1, company.getStore().size());
    }

    @Test
    void successfullyUpdateAProductInStock() {
        Product product = new Product(1, "MacBook Laptop", 800000.0, 5);
        company.getStore().add(product);
        Employee manager = new Employee("Ernest", "09900", Roles.MANAGER, 50000.0, 1, Gender.MALE);
        String value = managerService.addProductToStock("MacBook Laptop", 900000.0, 5, manager);
        System.out.println(value);
        assertNotNull(value);
        assertEquals("Product successfully updated", value);
        assertEquals(1, company.getStore().size());
    }

    @Test
    void shouldThrowAnExceptionWhenUnauthorizedEmployeeTriesToAddProductToStore() {
        Employee manager = new Employee("Ernest", "09900", Roles.CASHIER, 50000.0, 1, Gender.MALE);
        assertThrows(RuntimeException.class, ()-> managerService.addProductToStock("MacBook Laptop", 900000.0, 5, manager));
    }

    @Test
    void hireAnEmployee() {
    }

    @Test
    void fireAnEmployee() {
    }
}