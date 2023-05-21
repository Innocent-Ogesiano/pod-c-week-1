package org.java.models;

import org.java.enums.Gender;
import org.java.enums.Roles;

public class Employee extends Person {
    private Roles role;
    private double salary;
    private Integer employeeId;
    private Gender gender;

    public Employee(String name, String phoneNo, Roles role, double salary, Integer employeeId, Gender gender) {
        super(name, phoneNo);
        this.role = role;
        this.salary = salary;
        this.employeeId = employeeId;
        this.gender = gender;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name=" + this.getName() +
                ", phoneNo=" + this.getPhoneNo() +
                ", role=" + role +
                ", salary=" + salary +
                ", employeeId=" + employeeId +
                ", gender=" + gender +
                '}';
    }
}
