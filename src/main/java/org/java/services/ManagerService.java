package org.java.services;

import org.java.enums.Gender;
import org.java.enums.Roles;
import org.java.models.Employee;

public interface ManagerService {
    String addProductToStock (
            String productName, Double productPrice, Integer quantity, Employee employee
    );
    Employee hireAnEmployee(
            String employeeName, Gender gender, String employeePhoneNo, Roles roles, Employee employee
    );
    String fireAnEmployee(Employee employeeToBeFired, Employee employee);
}
