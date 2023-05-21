package org.java.servicesImpl;

import org.java.enums.Gender;
import org.java.enums.Roles;
import org.java.models.Company;
import org.java.models.Employee;
import org.java.models.Product;
import org.java.services.ManagerService;

import java.util.List;

public class ManagerServiceImpl implements ManagerService {
    private final Company company;

    public ManagerServiceImpl(Company company) {
        this.company = company;
    }

    @Override
    public String addProductToStock(
            String productName, Double productPrice, Integer quantity, Employee employee
    ) {
        String returnValue;
        if (employee.getRole().equals(Roles.MANAGER)) {
            List<Product> productList = company.getStore();
            Product newProduct = null;
            for (Product product : productList) {
                if (product.getName().equalsIgnoreCase(productName)) {
                    newProduct = product;
                    break;
                }
            }
            if (newProduct != null) {
                productList.remove(newProduct);
                newProduct.setPrice(productPrice);
                newProduct.setQuantity(newProduct.getQuantity() + quantity);
                returnValue = "Product successfully updated";
            } else {
                newProduct = new Product(productList.size() + 1, productName, productPrice, quantity);
                returnValue = "new product successfully added";
            }
            productList.add(newProduct);
            company.setStore(productList);
        } else {
            throw new RuntimeException("Unauthorized personnel");
        }
        return returnValue;
    }

    @Override
    public Employee hireAnEmployee(
            String employeeName, Gender gender, String employeePhoneNo, Roles roles, Employee employee
    ) {
        Employee newEmployee;
        if (employee.getRole().equals(Roles.MANAGER)) {
            List<Employee> employeeList = company.getEmployees();
            newEmployee = new Employee(employeeName, employeePhoneNo, roles, 30000, employeeList.size() + 1, gender);
            employeeList.add(newEmployee);
            company.setEmployees(employeeList);
        } else {
            throw new RuntimeException("Unauthorized personnel");
        }
        return newEmployee;
    }

    @Override
    public String fireAnEmployee(Employee employeeToBeFired, Employee employee) {
        if (employee.getRole().equals(Roles.MANAGER)) {
            List<Employee> employeeList = company.getEmployees();
            employeeList.remove(employeeToBeFired);
            company.setEmployees(employeeList);
        } else {
            throw new RuntimeException("Unauthorized personnel");
        }
        return employeeToBeFired.getName() + " has been fired from the company";
    }
}
