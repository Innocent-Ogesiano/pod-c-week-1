package org.java.services_impl;

import org.java.enums.Gender;
import org.java.enums.Roles;
import org.java.models.Company;
import org.java.models.Customer;
import org.java.models.Employee;
import org.java.services.CashierServices;
import org.java.services.ManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CashierServicesImplTest {
    private Company company;
    private CashierServices cashierServices;
    private Customer customer;
    private Employee cashier;

    @BeforeEach
    void setUp() {
        Employee manager = new Employee("Ernest", "09900", Roles.MANAGER, 50000.0, 1, Gender.MALE);
        String filePath = "src/main/resources/products.xlsx";
        company = new Company("DecaTech");
        new ManagerServiceImpl(company).loadProductsInStore(filePath, manager);
        cashierServices = new CashierServicesImpl(company);
        customer = new Customer(
                "Phillip", "090", "Benin", company.getCustomers().size()+1,
                1000000.0);
        new CustomerServiceImpl().addProductToCart("HP", 4, customer, company.getStore());
        cashier = new Employee("Charles", "09900", Roles.CASHIER, 50000.0, 1, Gender.MALE);
    }

    @Test
    void sellProductToCustomer() {
        assertEquals(4, company.getStore().get(1).getQuantity());
        assertEquals(1, customer.getCart().size());
        assertEquals(1000000, customer.getWalletBalance());
        assertEquals(0, company.getCompanyBalance());

        String value = cashierServices.sellProductToCustomer(customer, cashier);
        assertNotNull(value);
        assertEquals(0, customer.getCart().size());
        assertEquals(0, company.getStore().get(1).getQuantity());
        assertEquals(200000, customer.getWalletBalance());
        assertEquals(800000, company.getCompanyBalance());
    }
}