package org.java;

import org.java.dtos.ProductDto;
import org.java.enums.Gender;
import org.java.enums.Roles;
import org.java.models.Company;
import org.java.models.Customer;
import org.java.models.Employee;
import org.java.services.CashierServices;
import org.java.services.CustomerService;
import org.java.services.ManagerService;
import org.java.services_impl.CashierServicesImpl;
import org.java.services_impl.CustomerServiceImpl;
import org.java.services_impl.ManagerServiceImpl;

import static org.java.enums.Category.LAPTOPS;

public class Main {
    public static void main(String[] args) {
        Company company = new Company("Decagon");
        System.out.println(company);
        Employee manager = new Employee(
                "Ernest", "081419", Roles.MANAGER, 50000,
                company.getEmployees().size()+1, Gender.MALE);
        company.getEmployees().add(manager);
        ProductDto productDto = new ProductDto("MacBook Laptop", 500000.0, 5, LAPTOPS);
        ManagerService managerService = new ManagerServiceImpl(company);
        Employee cashier = managerService.hireAnEmployee("Charles", Gender.MALE, "070000", Roles.CASHIER, manager);
        Customer customer = new Customer(
                "Phillip", "090", "Benin", company.getCustomers().size()+1,
                1000000.0);
        company.getCustomers().add(customer);
        System.out.println(company);
        String filePath = "src/main/resources/products.xlsx";
        System.out.println(managerService.loadProductsInStore(filePath, manager));
//        System.out.println(company);
//        System.out.println(managerService.addProductToStock(productDto, manager));
//        System.out.println(company);

        CustomerService customerService = new CustomerServiceImpl();
        System.out.println(customerService.addProductToCart("MacBook", 1, customer, company.getStore()));
        System.out.println(customerService.addProductToCart("HP", 2, customer, company.getStore()));
        CashierServices cashierServices = new CashierServicesImpl(company);
        System.out.println(cashierServices.sellProductToCustomer(customer, cashier));
        System.out.println(company);
//        try {
//            System.out.println(managerService.fireAnEmployee(manager, cashier));
//        } catch (RuntimeException e) {
//            System.out.println("Exception caught " + e.getMessage());
//        }
//        System.out.println("This is a random string");
//        System.out.println(managerService.fireAnEmployee(cashier, manager));
//        System.out.println(company);
    }
}