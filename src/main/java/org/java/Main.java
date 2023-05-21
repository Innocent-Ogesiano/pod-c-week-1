package org.java;

import org.java.enums.Gender;
import org.java.enums.Roles;
import org.java.models.Company;
import org.java.models.Employee;
import org.java.services.ManagerService;
import org.java.servicesImpl.ManagerServiceImpl;

public class Main {
    public static void main(String[] args) {
        Company company = new Company("Decagon");
        System.out.println(company);
        Employee manager = new Employee(
                "Ernest", "081419", Roles.MANAGER, 50000,
                company.getEmployees().size()+1, Gender.MALE);
        company.getEmployees().add(manager);
        System.out.println(company);
        ManagerService managerService = new ManagerServiceImpl(company);
        System.out.println(managerService.addProductToStock("MacBook Laptop", 800000.0, 5, manager));
        System.out.println(company);
        System.out.println(managerService.addProductToStock("MacBook Laptop", 900000.0, 5, manager));
        System.out.println(company);
        Employee cashier = managerService.hireAnEmployee("Charles", Gender.MALE, "070000", Roles.CASHIER, manager);
        System.out.println(company);
        try {
            System.out.println(managerService.fireAnEmployee(manager, cashier));
        } catch (RuntimeException e) {
            System.out.println("Exception caught " + e.getMessage());
        }
        System.out.println("This is a random string");
        System.out.println(managerService.fireAnEmployee(cashier, manager));
        System.out.println(company);
    }
}