package org.java.services_impl;

import org.java.dtos.ProductDto;
import org.java.enums.Category;
import org.java.enums.Gender;
import org.java.enums.Roles;
import org.java.models.Company;
import org.java.models.Employee;
import org.java.models.Product;
import org.java.services.ManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.java.enums.Category.LAPTOPS;
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
        ProductDto productDto = new ProductDto("MacBook Laptop", 500000.0, 5, LAPTOPS);
        Employee manager = new Employee("Ernest", "09900", Roles.MANAGER, 50000.0, 1, Gender.MALE);
        String value = managerService.addProductToStock(productDto, manager);
        System.out.println(value);
        assertNotNull(value);
        assertEquals("new product successfully added", value);
        assertEquals(1, company.getStore().size());
    }

    @Test
    void successfullyUpdateAProductInStock() {
        Product product = new Product(1, "MacBook Laptop", 800000.0, 5, Category.LAPTOPS);
        ProductDto productDto = new ProductDto("MacBook Laptop", 500000.0, 5, LAPTOPS);
        company.getStore().add(product);
        Employee manager = new Employee("Ernest", "09900", Roles.MANAGER, 50000.0, 1, Gender.MALE);
        String value = managerService.addProductToStock(productDto, manager);
        System.out.println(value);
        assertNotNull(value);
        assertEquals("Product successfully updated", value);
        assertEquals(1, company.getStore().size());
    }

    @Test
    void shouldThrowAnExceptionWhenUnauthorizedEmployeeTriesToAddProductToStore() {
        ProductDto productDto = new ProductDto("MacBook Laptop", 500000.0, 5, LAPTOPS);
        Employee manager = new Employee("Ernest", "09900", Roles.CASHIER, 50000.0, 1, Gender.MALE);
        assertThrows(RuntimeException.class, ()-> managerService.addProductToStock(productDto, manager));
    }

    @Test
    void hireAnEmployee() {
    }

    @Test
    void fireAnEmployee() {
    }

    @Test
    void loadProductsInStore() {
        Employee manager = new Employee("Ernest", "09900", Roles.MANAGER, 50000.0, 1, Gender.MALE);
        String filePath = "src/main/resources/products.xlsx";
        assertEquals(0, company.getStore().size());
        String result = managerService.loadProductsInStore(filePath, manager);
        String expected = "Products successfully loaded to store";
        assertNotNull(result);
        assertEquals(expected, result);
        assertEquals(10, company.getStore().size());
    }
}