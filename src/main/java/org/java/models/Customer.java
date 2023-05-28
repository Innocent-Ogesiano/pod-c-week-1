package org.java.models;

import org.java.dtos.ProductDto;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {
    private String address;
    private Integer customerId;
    private Double walletBalance;
    private List<ProductDto> cart;

    public Customer(String name, String phoneNo, String address, Integer customerId, Double walletBalance) {
        super(name, phoneNo);
        this.address = address;
        this.customerId = customerId;
        this.walletBalance = walletBalance;
        this.cart = new ArrayList<>();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(Double walletBalance) {
        this.walletBalance = walletBalance;
    }

    public List<ProductDto> getCart() {
        return cart;
    }

    public void setCart(List<ProductDto> cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "address='" + address + '\'' +
                ", customerId=" + customerId +
                ", walletBalance=" + walletBalance +
                ", cart=" + cart +
                '}';
    }
}
