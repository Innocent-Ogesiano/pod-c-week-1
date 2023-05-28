package org.java.services_impl;

import org.java.dtos.ProductDto;
import org.java.enums.Roles;
import org.java.models.Company;
import org.java.models.Customer;
import org.java.models.Employee;
import org.java.models.Product;
import org.java.services.CashierServices;

import java.util.ArrayList;
import java.util.List;

public class CashierServicesImpl implements CashierServices {
    private final Company company;

    public CashierServicesImpl(Company company) {
        this.company = company;
    }

    @Override
    public String sellProductToCustomer(Customer customer, Employee employee) {
        if (employee.getRole().equals(Roles.CASHIER)) {
            List<ProductDto> productsInCart = customer.getCart();
            double totalPrice = productsInCart.stream()
                    .mapToDouble(productDto -> productDto.getPrice() * productDto.getQuantity())
                    .sum();
            if (customer.getWalletBalance() >= totalPrice) {
                customer.setWalletBalance(customer.getWalletBalance() - totalPrice);
                company.setCompanyBalance(company.getCompanyBalance() + totalPrice);
                List<Product> productsInStore = company.getStore();
                productsInCart.forEach(productDto -> productsInStore.stream()
                                .filter(product -> product.getName().equals(productDto.getName()))
                                .findFirst()
                                .ifPresent(product -> product.setQuantity(product.getQuantity() - productDto.getQuantity())));
                company.setStore(productsInStore);
                String receipt = printReceipt(customer, totalPrice);
                customer.getCart().clear();
                return receipt;
            } else {
                return "Insufficient balance to make purchase";
            }
        } else {
            throw new RuntimeException("Unauthorized personnel");
        }
    }

    private String printReceipt(Customer customer, double totalPrice) {
        return "Customer Name = " + customer.getName() +
                "\n Products Bought = " + customer.getCart() +
                "\n Total price = " + totalPrice;
    }
}
