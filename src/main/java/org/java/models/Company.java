package org.java.models;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private final String companyName;
    private List<Employee> employees;
    private List<Product> store;
    private List<Customer> customers;

    public Company(String companyName) {
        this.employees = new ArrayList<>();
        this.store = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.companyName = companyName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Product> getStore() {
        return store;
    }

    public void setStore(List<Product> store) {
        this.store = store;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public String getCompanyName() {
        return companyName;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyName='" + companyName + '\'' +
                ", employees=" + employees +
                ", store=" + store +
                ", customers=" + customers +
                '}';
    }
}
