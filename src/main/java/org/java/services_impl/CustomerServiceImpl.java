package org.java.services_impl;

import org.java.dtos.ProductDto;
import org.java.models.Company;
import org.java.models.Customer;
import org.java.models.Product;
import org.java.services.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    @Override
    public String addProductToCart(String productName, int quantity, Customer customer, List<Product> productList) {
        for (Product product : productList) {
            if (product.getName().equals(productName)) {
                if (product.getQuantity() >= quantity) {
                    customer.getCart().add(new ProductDto(product, quantity));
                    return productName + " Successfully added to cart";
                } else if (product.getQuantity() > 0) {
                    return "We only have " + product.getQuantity() + " of " + productName + " available";
                } else {
                    return productName + " OUT OF STOCK";
                }
            }
        }
        return productName + " NOT AVAILABLE";
    }
}
