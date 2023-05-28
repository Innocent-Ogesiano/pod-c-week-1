package org.java.services_impl;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.java.dtos.ProductDto;
import org.java.enums.Category;
import org.java.enums.Gender;
import org.java.enums.Roles;
import org.java.models.Company;
import org.java.models.Employee;
import org.java.models.Product;
import org.java.services.ManagerService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class ManagerServiceImpl implements ManagerService {
    private final Company company;

    public ManagerServiceImpl(Company company) {
        this.company = company;
    }

    @Override
    public String loadProductsInStore(String filePath, Employee employee) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workBook = new XSSFWorkbook(fileInputStream)) {
            Sheet sheet = workBook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() > 0) {
                    int id = (int) row.getCell(0).getNumericCellValue();
                    String productName = row.getCell(1).getStringCellValue();
                    double price = row.getCell(2).getNumericCellValue();
                    int quantity = (int) row.getCell(3).getNumericCellValue();
                    String category = row.getCell(4).getStringCellValue();
                    ProductDto productDto = new ProductDto(id, productName, price, quantity, Category.valueOf(category));
                    addProductToStock(productDto, employee);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
        return "Products successfully loaded to store";
    }

    @Override
    public String addProductToStock(ProductDto productDto, Employee employee) {
        String returnValue;
        if (employee.getRole().equals(Roles.MANAGER)) {
            List<Product> productList = company.getStore();
            Product newProduct = null;
            for (Product product : productList) {
                if (product.getName().equalsIgnoreCase(productDto.getName())) {
                    newProduct = product;
                    break;
                }
            }
            if (newProduct != null) {
                productList.remove(newProduct);
                newProduct.setPrice(productDto.getPrice());
                newProduct.setQuantity(newProduct.getQuantity() + productDto.getQuantity());
                System.out.println("Product successfully updated");
                returnValue = "Product successfully updated";
            } else {
                newProduct = new Product(
                        productDto.getId(), productDto.getName(), productDto.getPrice(),
                        productDto.getQuantity(), productDto.getCategory());
                System.out.println("new product successfully added");
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
