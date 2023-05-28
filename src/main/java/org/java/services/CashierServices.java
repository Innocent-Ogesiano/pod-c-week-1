package org.java.services;

import org.java.models.Customer;
import org.java.models.Employee;

public interface CashierServices {
    String sellProductToCustomer(Customer customer, Employee employee);
}
