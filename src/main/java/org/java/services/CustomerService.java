package org.java.services;

import org.java.models.Customer;
import org.java.models.Product;

import java.util.List;

public interface CustomerService {
    String addProductToCart(String productName, int quantity, Customer customer, List<Product> productList);
}
