package org.java.services_impl;

import org.java.enums.Gender;
import org.java.enums.Roles;
import org.java.models.Company;
import org.java.models.Customer;
import org.java.models.Employee;
import org.java.services.CustomerService;
import org.java.services.ManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImplTest {
    private Company company;
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        Employee manager = new Employee("Ernest", "09900", Roles.MANAGER, 50000.0, 1, Gender.MALE);
        String filePath = "src/main/resources/products.xlsx";
        company = new Company("DecaTech");
        ManagerService managerService = new ManagerServiceImpl(company);
        managerService.loadProductsInStore(filePath, manager);
        customerService = new CustomerServiceImpl();
    }

    @Test
    void addProductToCart() {
        Customer customer = new Customer(
                "Phillip", "090", "Benin", company.getCustomers().size()+1,
                1000000.0);
        assertEquals(0, customer.getCart().size());
        String value = customerService.addProductToCart("MacBook", 1, customer, company.getStore());
        String expected = "MacBook Successfully added to cart";
        assertNotNull(value);
        assertEquals(1, customer.getCart().size());
        assertEquals(expected, value);
        String value2 = customerService.addProductToCart("HP", 5, customer, company.getStore());
        String expected2 = "We only have 4 of HP available";
        assertNotNull(value2);
        assertEquals(expected2, value2);
    }
}